package com.example.data

import android.util.Log
import com.example.model.AdConceptDemo
import com.example.model.AdConceptRequest
import com.example.model.BilingualText
import com.example.model.BusinessCategory
import com.example.model.ScriptLanguage
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.Schema
import com.google.firebase.ai.type.generationConfig
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import org.json.JSONObject

private const val TAG = "AiConceptRepo"

/**
 * Contract for generating tailored Ad Demo concepts for Flickora AI Studio clients.
 */
interface AiConceptRepository {
    suspend fun generateAdConcept(request: AdConceptRequest): Result<AdConceptDemo>
}

/**
 * Live Gemini implementation powered by Firebase AI Logic (`gemini-3.8-flash`)
 * using structured JSON output (`Schema.obj`).
 */
class FirebaseAiConceptRepository : AiConceptRepository {

    private val adConceptSchema = Schema.obj(
        properties = mapOf(
            "hookHeadline" to Schema.string(
                description = "Catchy commercial headline for the business or product in the requested script language"
            ),
            "adTagline" to Schema.string(
                description = "Short 3-6 word creative tagline for the ad campaign"
            ),
            "voiceoverScript" to Schema.string(
                description = "Energetic 20-25 second studio voiceover script in the requested script language"
            ),
            "scenes" to Schema.array(
                items = Schema.string(),
                description = "Exactly 3 sequential visual storyboard scene descriptions with timing (e.g. Scene 1 (0-6s): ...)"
            ),
            "posterConcept" to Schema.string(
                description = "Visual art direction for an accompanying high-converting AI promotional poster"
            ),
            "hashtags" to Schema.array(
                items = Schema.string(),
                description = "4 to 6 relevant social media hashtags starting with #"
            )
        )
    )

    private val model by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            generationConfig = generationConfig {
                responseMimeType = "application/json"
                responseSchema = adConceptSchema
            }
        )
    }

    override suspend fun generateAdConcept(request: AdConceptRequest): Result<AdConceptDemo> {
        val business = request.businessName.trim().ifBlank { "My Brand" }
        val offer = request.offerHighlight.trim()
        val service = StudioCatalog.services.firstOrNull { it.id == request.serviceId }
            ?: StudioCatalog.services.first()

        val languageInstruction = when (request.scriptLanguage) {
            ScriptLanguage.GUJARATI -> "Gujarati (using authentic natural Gujarati script)"
            ScriptLanguage.HINDI -> "Hindi (using natural Devanagari script)"
            ScriptLanguage.ENGLISH -> "English"
        }

        val prompt = buildString {
            appendLine("You are the lead creative director at Flickora AI Studio ('Create • Edit • Inspire').")
            appendLine("Generate a premium, high-converting miniature advertisement demo concept for a client.")
            appendLine("Client Business / Product Name: $business")
            appendLine("Business Category: ${request.category.label.en}")
            appendLine("Requested Studio Service: ${service.title.en} (${service.subtitle.en})")
            if (offer.isNotBlank()) {
                appendLine("Special Offer / Highlight: $offer")
            }
            appendLine("Write hookHeadline and voiceoverScript strictly in $languageInstruction.")
            appendLine("Write adTagline, the 3 storyboard scenes, and posterConcept clearly in concise English (or bilingual where impactful).")
            appendLine("Keep the voiceoverScript punchy and realistic for a 20-25 second Instagram Reel or WhatsApp promo.")
        }

        return try {
            val response = withTimeout(60_000L) {
                model.generateContent(prompt)
            }
            val jsonString = response.text
                ?: throw IllegalStateException("Gemini returned an empty response.")

            val json = JSONObject(jsonString)
            val hookHeadline = json.getString("hookHeadline")
            val adTagline = json.getString("adTagline")
            val voiceoverScript = json.getString("voiceoverScript")

            val scenesArray = json.getJSONArray("scenes")
            val scenes = (0 until scenesArray.length()).map { scenesArray.getString(it) }

            val posterConcept = json.getString("posterConcept")

            val hashtagsArray = json.getJSONArray("hashtags")
            val hashtags = (0 until hashtagsArray.length()).map { hashtagsArray.getString(it) }

            Result.success(
                AdConceptDemo(
                    businessName = business,
                    serviceTitle = BilingualText(service.title.gu, service.title.en),
                    hookHeadline = hookHeadline,
                    adTagline = adTagline,
                    voiceoverScript = voiceoverScript,
                    scenes = scenes,
                    posterConcept = posterConcept,
                    hashtags = hashtags,
                    isSampleMode = false
                )
            )
        } catch (e: TimeoutCancellationException) {
            Log.e(TAG, "Gemini ad concept generation timed out", e)
            Result.failure(
                IllegalStateException("Generation timed out after 60 seconds. Please try again.", e)
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Log.e(TAG, "Gemini ad concept generation failed", e)
            Result.failure(e)
        }
    }
}

/**
 * Deterministic repository implementation used in local JVM/Robolectric unit tests.
 */
class SampleAiConceptRepository : AiConceptRepository {

    override suspend fun generateAdConcept(request: AdConceptRequest): Result<AdConceptDemo> {
        delay(550L)

        val business = request.businessName.trim().ifBlank { "તમારી બ્રાન્ડ / Your Brand" }
        val offer = request.offerHighlight.trim()
        val service = StudioCatalog.services.firstOrNull { it.id == request.serviceId }
            ?: StudioCatalog.services.first()

        val hookHeadline = when (request.scriptLanguage) {
            ScriptLanguage.GUJARATI -> when (request.category) {
                BusinessCategory.FASHION -> "$business — નવું રોયલ કલેક્શન, જે દરેકની નજર રોકી લે!"
                BusinessCategory.JEWELRY -> "$business — શુદ્ધતા અને ચમકનો અનોખો સંગમ"
                BusinessCategory.ELECTRONICS -> "$business — સ્માર્ટ ટેક, સૌથી શ્રેષ્ઠ ઓફર સાથે!"
                BusinessCategory.CAFE_FOOD -> "$business — એવો સ્વાદ, જે દરેક મુલાકાતને ખાસ બનાવે"
                BusinessCategory.BEAUTY -> "$business — નેચરલ ગ્લો અને પ્રીમિયમ કેર"
                BusinessCategory.LOCAL_SHOP -> "$business — તમારા શહેરનો ભરોસાપાત્ર શોરૂમ!"
            }
            ScriptLanguage.HINDI -> when (request.category) {
                BusinessCategory.FASHION -> "$business — नया रॉयल कलेक्शन, हर नज़र ठहर जाए!"
                BusinessCategory.JEWELRY -> "$business — शुद्धता और चमक की नई पहचान"
                BusinessCategory.ELECTRONICS -> "$business — स्मार्ट टेक, शानदार ऑफर्स के साथ!"
                BusinessCategory.CAFE_FOOD -> "$business — ऐसा स्वाद जो हर पल को खास बना दे"
                BusinessCategory.BEAUTY -> "$business — नेचुरल ग्लो और प्रीमियम केयर"
                BusinessCategory.LOCAL_SHOP -> "$business — आपके शहर का भरोसेमंद शोरूम!"
            }
            ScriptLanguage.ENGLISH -> when (request.category) {
                BusinessCategory.FASHION -> "$business — Signature Looks Crafted to Turn Heads"
                BusinessCategory.JEWELRY -> "$business — Timeless Craftsmanship, Radiant Presence"
                BusinessCategory.ELECTRONICS -> "$business — Smarter Tech, Unbeatable Value"
                BusinessCategory.CAFE_FOOD -> "$business — Handcrafted Flavor Worth Sharing"
                BusinessCategory.BEAUTY -> "$business — Effortless Glow, Pure Confidence"
                BusinessCategory.LOCAL_SHOP -> "$business — Your City's Favorite Destination"
            }
        }

        val offerLineGu = if (offer.isNotBlank()) " ખાસ ઓફર: $offer." else ""
        val offerLineHi = if (offer.isNotBlank()) " खास ऑफर: $offer." else ""
        val offerLineEn = if (offer.isNotBlank()) " Highlight: $offer." else ""

        val voiceoverScript = when (request.scriptLanguage) {
            ScriptLanguage.GUJARATI ->
                "નમસ્તે! જો તમે ${request.category.label.gu} માટે કંઈક ખાસ શોધી રહ્યા છો, તો $business તમારા માટે લાવ્યું છે એક પ્રીમિયમ અનુભવ.$offerLineGu આજે જ મુલાકાત લો અથવા WhatsApp પર સંપર્ક કરો!"
            ScriptLanguage.HINDI ->
                "नमस्ते! अगर आप ${request.category.label.en} में कुछ प्रीमियम ढूँढ रहे हैं, तो $business लेकर आया है आपके लिए खास कलेक्शन.$offerLineHi आज ही विज़िट करें या WhatsApp करें!"
            ScriptLanguage.ENGLISH ->
                "Elevate your everyday with $business — crafted for lovers of ${request.category.label.en.lowercase()}.$offerLineEn Visit us today or tap WhatsApp to order your favorites!"
        }

        val scenes = listOf(
            "Scene 1 (0–6s): Dramatic spotlight reveal introducing $business with sleek kinetic title.",
            "Scene 2 (6–18s): Dynamic 3D/AI visual showcase of key highlights${if (offer.isNotBlank()) " + '$offer'" else ""}.",
            "Scene 3 (18–25s): High-converting end-card with $business logo, Instagram & WhatsApp details."
        )

        val posterConcept = "Obsidian & champagne-gold editorial frame showcasing $business."
        val tagBase = business.replace(Regex("[^A-Za-z0-9]"), "").ifBlank { "YourBrand" }
        val hashtags = listOf("#FlickoraAIStudio", "#$tagBase", "#AIAdvertisement", "#CreateEditInspire")

        return Result.success(
            AdConceptDemo(
                businessName = business,
                serviceTitle = BilingualText(service.title.gu, service.title.en),
                hookHeadline = hookHeadline,
                adTagline = "Sample Concept • ${service.title.en}",
                voiceoverScript = voiceoverScript,
                scenes = scenes,
                posterConcept = posterConcept,
                hashtags = hashtags,
                isSampleMode = true
            )
        )
    }
}
