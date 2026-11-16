package com.example.model

import androidx.annotation.DrawableRes

enum class AppLanguage(val shortLabel: String, val fullLabel: String) {
    GUJARATI("ગુજ", "ગુજરાતી"),
    ENGLISH("EN", "English")
}

data class BilingualText(
    val gu: String,
    val en: String
) {
    fun resolve(language: AppLanguage): String = when (language) {
        AppLanguage.GUJARATI -> gu
        AppLanguage.ENGLISH -> en
    }
}

enum class ServiceCategory(val label: BilingualText) {
    ALL(BilingualText("બધી સર્વિસ", "All Services")),
    VIDEO(BilingualText("AI વીડિયો", "AI Videos")),
    DESIGN_VOICE(BilingualText("પોસ્ટર્સ અને વોઇસ", "Posters & Voice"))
}

enum class ServiceIconType {
    AI_MODEL,
    PRODUCT_VIDEO,
    SHOP_VIDEO,
    AI_POSTER,
    CINEMATIC_AD,
    VOICEOVER,
    VIDEO_EDITING
}

data class StudioService(
    val id: String,
    val title: BilingualText,
    val subtitle: BilingualText,
    val description: BilingualText,
    val category: ServiceCategory,
    val iconType: ServiceIconType,
    val turnaround: BilingualText,
    val idealFor: List<BilingualText>,
    val deliverables: List<BilingualText>
)

data class StoryboardShot(
    val sceneNumber: Int,
    val durationLabel: String,
    val visualDirection: BilingualText,
    val cameraNote: BilingualText
)

data class ShowcaseItem(
    val id: String,
    val serviceId: String,
    val title: BilingualText,
    val tag: BilingualText,
    val formatBadge: String,
    val durationLabel: String,
    @DrawableRes val imageRes: Int,
    val summary: BilingualText,
    val sampleVoiceover: BilingualText,
    val shots: List<StoryboardShot>
)

enum class BusinessCategory(val label: BilingualText) {
    FASHION(BilingualText("ફેશન અને બુટિક", "Fashion & Boutique")),
    JEWELRY(BilingualText("જ્વેલરી શોરૂમ", "Jewelry Showroom")),
    ELECTRONICS(BilingualText("મોબાઇલ અને ઇલેક્ટ્રોનિક્સ", "Electronics & Gadgets")),
    CAFE_FOOD(BilingualText("કાફે અને રેસ્ટોરન્ટ", "Cafe & Restaurant")),
    BEAUTY(BilingualText("બ્યુટી અને સ્કિનકેર", "Beauty & Skincare")),
    LOCAL_SHOP(BilingualText("લોકલ શોપ / શોરૂમ", "Retail Shop & Showroom"))
}

enum class ScriptLanguage(val label: BilingualText) {
    GUJARATI(BilingualText("ગુજરાતી સ્ક્રિપ્ટ", "Gujarati")),
    HINDI(BilingualText("હિન્દી સ્ક્રિપ્ટ", "Hindi")),
    ENGLISH(BilingualText("અંગ્રેજી સ્ક્રિપ્ટ", "English"))
}

data class AdConceptRequest(
    val businessName: String,
    val category: BusinessCategory,
    val serviceId: String,
    val scriptLanguage: ScriptLanguage,
    val offerHighlight: String
)

data class AdConceptDemo(
    val businessName: String,
    val serviceTitle: BilingualText,
    val hookHeadline: String,
    val adTagline: String,
    val voiceoverScript: String,
    val scenes: List<String>,
    val posterConcept: String,
    val hashtags: List<String>,
    val isSampleMode: Boolean = true
)

data class WhatsAppDesk(
    val number: String,
    val formattedNumber: String,
    val role: BilingualText
)
