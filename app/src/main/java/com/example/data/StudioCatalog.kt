package com.example.data

import com.example.R
import com.example.model.BilingualText
import com.example.model.ServiceCategory
import com.example.model.ServiceIconType
import com.example.model.ShowcaseItem
import com.example.model.StoryboardShot
import com.example.model.StudioService
import com.example.model.WhatsAppDesk

object StudioCatalog {

    const val STUDIO_NAME = "Flickora AI Studio"
    const val STUDIO_TAGLINE = "Create • Edit • Inspire"
    const val INSTAGRAM_HANDLE = "@flickora_ai_studio"
    const val INSTAGRAM_URL = "https://www.instagram.com/flickora_ai_studio/"

    val whatsappDesks = listOf(
        WhatsAppDesk(
            number = "6355675109",
            formattedNumber = "+91 63556 75109",
            role = BilingualText(
                gu = "પ્રાઇમરી સ્ટુડિયો ડેસ્ક • ફ્રી ડેમો",
                en = "Primary Studio Desk • Free Demo"
            )
        ),
        WhatsAppDesk(
            number = "9737526661",
            formattedNumber = "+91 97375 26661",
            role = BilingualText(
                gu = "ક્રિએટિવ પ્રોડક્શન અને બુકિંગ",
                en = "Creative Production & Booking"
            )
        )
    )

    val services: List<StudioService> = listOf(
        StudioService(
            id = "ai_model_ad",
            title = BilingualText("AI મોડેલ એડવર્ટાઇઝમેન્ટ", "AI Model Advertisement"),
            subtitle = BilingualText("તમારી બ્રાન્ડ માટે લાઇફલાઇક AI મોડેલ્સ", "Photorealistic AI brand ambassadors"),
            description = BilingualText(
                gu = "કપડાં, જ્વેલરી, કોસ્મેટિક્સ અને ફેશન બ્રાન્ડ્સ માટે શૂટિંગ વગર પ્રીમિયમ AI મોડેલ જાહેરાતો.",
                en = "Studio-grade fashion & lifestyle commercials featuring photorealistic AI models wearing or presenting your products—no physical shoot required."
            ),
            category = ServiceCategory.VIDEO,
            iconType = ServiceIconType.AI_MODEL,
            turnaround = BilingualText("24-48 કલાક", "24–48 hrs"),
            idealFor = listOf(
                BilingualText("કપડાં અને બુટિક", "Boutiques & Apparel"),
                BilingualText("જ્વેલરી", "Jewelry Brands"),
                BilingualText("સ્કિનકેર", "Skincare & Beauty")
            ),
            deliverables = listOf(
                BilingualText("9:16 Instagram Reels અને WhatsApp Status", "9:16 Reels & WhatsApp Status cut"),
                BilingualText("કસ્ટમ AI મોડેલ લુક અને લાઇટિંગ", "Custom AI lookbook & studio lighting"),
                BilingualText("બેકગ્રાઉન્ડ મ્યુઝિક અને વોઇસઓવર", "Licensed BGM & voiceover sync")
            )
        ),
        StudioService(
            id = "product_ad_video",
            title = BilingualText("પ્રોડક્ટ એડવર્ટાઇઝમેન્ટ વીડિયો", "Product Advertisement Videos"),
            subtitle = BilingualText("3D અને AI સ્ટુડિયો લાઇટિંગ કોમર્શિયલ", "Dynamic 3D/AI product showcase reels"),
            description = BilingualText(
                gu = "તમારી પ્રોડક્ટને હાઇલાઇટ કરતા 3D મોશન, વોટર સ્પ્લેશ અને સિનેમેટિક ઝૂમ વીડિયો.",
                en = "High-converting product reveal reels with sleek 3D motion graphics, dramatic macro close-ups, and clean call-to-action slides."
            ),
            category = ServiceCategory.VIDEO,
            iconType = ServiceIconType.PRODUCT_VIDEO,
            turnaround = BilingualText("24-48 કલાક", "24–48 hrs"),
            idealFor = listOf(
                BilingualText("પરફ્યુમ અને વોચ", "Perfumes & Watches"),
                BilingualText("ઇલેક્ટ્રોનિક્સ", "Gadgets & Tech"),
                BilingualText("ફૂડ અને FMCG", "Packaged Foods & FMCG")
            ),
            deliverables = listOf(
                BilingualText("4K અપસ્કેલ્ડ કોમર્શિયલ રીલ", "Crisp Full-HD / 4K ready reel"),
                BilingualText("પ્રોડક્ટ ફીચર કોલઆઉટ ટેક્સ્ટ", "Animated feature callouts"),
                BilingualText("બિઝનેસ લોગો આઉટ્રો", "Branded logo & contact end-card")
            )
        ),
        StudioService(
            id = "shop_ad_video",
            title = BilingualText("શોપ એડવર્ટાઇઝમેન્ટ વીડિયો", "Shop Advertisement Videos"),
            subtitle = BilingualText("તમારી દુકાન અને શોરૂમ માટે આકર્ષક જાહેરાત", "Local store & showroom promos"),
            description = BilingualText(
                gu = "તમારી શોપ, શોરૂમ કે બિઝનેસની ઓફર્સ અને લોકેશનને વધુ ગ્રાહકો સુધી પહોંચાડતા વીડિયો.",
                en = "Attention-grabbing promotional videos crafted to bring local footfall and WhatsApp inquiries straight to your shop or showroom."
            ),
            category = ServiceCategory.VIDEO,
            iconType = ServiceIconType.SHOP_VIDEO,
            turnaround = BilingualText("24 કલાક", "24 hrs"),
            idealFor = listOf(
                BilingualText("શોરૂમ અને મોલ", "Retail Showrooms"),
                BilingualText("કાફે અને રેસ્ટોરન્ટ", "Cafes & Restaurants"),
                BilingualText("મોબાઇલ અને ફર્નિચર શોપ", "Electronics & Furniture")
            ),
            deliverables = listOf(
                BilingualText("લોકેશન અને ઓફર હાઇલાઇટ્સ", "Store address & offer badges"),
                BilingualText("ગુજરાતી/હિન્દી આકર્ષક વોઇસઓવર", "Engaging Gujarati/Hindi voiceover"),
                BilingualText("સોશિયલ મીડિયા શેર-રેડી ફોર્મેટ", "Reels, Shorts & Status ready")
            )
        ),
        StudioService(
            id = "premium_ai_posters",
            title = BilingualText("પ્રીમિયમ AI પોસ્ટર્સ", "Premium AI Posters"),
            subtitle = BilingualText("તહેવાર, ઓફર અને લોન્ચ પોસ્ટર્સ", "Scroll-stopping festival & launch art"),
            description = BilingualText(
                gu = "ફેસ્ટિવલ ઓફર્સ, ન્યૂ અરાઇવલ અને બ્રાન્ડિંગ માટે અલગ અને પ્રીમિયમ લુક આપતા AI પોસ્ટર્સ.",
                en = "Editorial-quality commercial posters and carousel graphics engineered to stop thumb-scrolling on Instagram and WhatsApp."
            ),
            category = ServiceCategory.DESIGN_VOICE,
            iconType = ServiceIconType.AI_POSTER,
            turnaround = BilingualText("12-24 કલાક", "12–24 hrs"),
            idealFor = listOf(
                BilingualText("ફેસ્ટિવલ સેલ", "Festival & Diwali Sales"),
                BilingualText("ગ્રાન્ડ ઓપનિંગ", "Grand Openings"),
                BilingualText("ડેઇલી સ્ટેટસ માર્કેટિંગ", "Daily WhatsApp Status")
            ),
            deliverables = listOf(
                BilingualText("1:1 પોસ્ટ અને 9:16 સ્ટોરી સાઇઝ", "1:1 Feed + 9:16 Story variants"),
                BilingualText("પ્રિન્ટ-રેડી હાઇ રિઝોલ્યુશન", "High-res print & digital export"),
                BilingualText("કસ્ટમ ગુજરાતી/અંગ્રેજી ટાઇપોગ્રાફી", "Bilingual typography layout")
            )
        ),
        StudioService(
            id = "cinematic_ad_videos",
            title = BilingualText("સિનેમેટિક એડવર્ટાઇઝમેન્ટ વીડિયો", "Cinematic Advertisement Videos"),
            subtitle = BilingualText("બ્રાન્ડ માટે મૂવી-ગ્રેડ સ્ટોરીટેલિંગ", "Movie-grade brand storytelling"),
            description = BilingualText(
                gu = "તમારા બિઝનેસને મોટી બ્રાન્ડ જેવો રોયલ અને સિનેમેટિક લુક આપતી હાઇ-ઇમ્પેક્ટ જાહેરાતો.",
                en = "Widescreen & vertical brand films blending cinematic color grading, dramatic AI visuals, and emotional storytelling."
            ),
            category = ServiceCategory.VIDEO,
            iconType = ServiceIconType.CINEMATIC_AD,
            turnaround = BilingualText("48-72 કલાક", "48–72 hrs"),
            idealFor = listOf(
                BilingualText("રિયલ એસ્ટેટ", "Real Estate & Builders"),
                BilingualText("લક્ઝરી બ્રાન્ડ્સ", "Luxury Jewelry & Bridal"),
                BilingualText("કોર્પોરેટ લોન્ચ", "Brand Launches")
            ),
            deliverables = listOf(
                BilingualText("સિનેમેટિક કલર ગ્રેડિંગ અને VFX", "Cinema color grade & subtle VFX"),
                BilingualText("સાઉન્ડ ડિઝાઇન અને સબટાઇટલ્સ", "Rich SFX & dynamic subtitles"),
                BilingualText("16:9 અને 9:16 ડ્યુઅલ માસ્ટર", "Both 16:9 & 9:16 masters")
            )
        ),
        StudioService(
            id = "professional_voiceover",
            title = BilingualText("પ્રોફેશનલ વોઇસઓવર", "Professional Voiceover"),
            subtitle = BilingualText("ગુજરાતી, હિન્દી અને અંગ્રેજી સ્ટુડિયો વોઇસ", "Crystal-clear multi-language VO"),
            description = BilingualText(
                gu = "જાહેરાતમાં જાન ફૂંકી દે તેવો સ્પષ્ટ, એનર્જેટિક અને પ્રોફેશનલ સ્ટુડિયો વોઇસઓવર.",
                en = "Broadcast-clean narration in natural Gujarati, Hindi, or English—mixed with crisp studio EQ and background music."
            ),
            category = ServiceCategory.DESIGN_VOICE,
            iconType = ServiceIconType.VOICEOVER,
            turnaround = BilingualText("12 કલાક", "12 hrs"),
            idealFor = listOf(
                BilingualText("બ્રાન્ડ એક્સપ્લેનર", "Brand Explainers"),
                BilingualText("ઓફર એનાઉન્સમેન્ટ", "Sale Announcements"),
                BilingualText("પોડકાસ્ટ અને રીલ્સ", "Reels & Narration")
            ),
            deliverables = listOf(
                BilingualText("સ્ક્રિપ્ટ રાઇટિંગ સહાય", "Ad script polish included"),
                BilingualText("મેલ / ફીમેલ વોઇસ ઓપ્શન્સ", "Warm male or female voice tone"),
                BilingualText("BGM મિક્સ્ડ WAV/MP3", "Studio mastered audio mix")
            )
        ),
        StudioService(
            id = "creative_video_editing",
            title = BilingualText("ક્રિએટિવ AI વીડિયો એડિટિંગ", "Creative AI Video Editing"),
            subtitle = BilingualText("ફાસ્ટ ટ્રાન્ઝિશન, VFX અને મોશન ટેક્સ્ટ", "High-retention reels & VFX edits"),
            description = BilingualText(
                gu = "તમારા મોબાઇલ શૂટ કરેલા વીડિયોને AI એડિટિંગ, ટ્રેન્ડિંગ કટ્સ અને કલર ગ્રેડિંગથી પ્રોફેશનલ બનાવો.",
                en = "Transform raw mobile clips into viral-grade commercial reels with beat-synced cuts, AI background polish, and bold kinetic captions."
            ),
            category = ServiceCategory.VIDEO,
            iconType = ServiceIconType.VIDEO_EDITING,
            turnaround = BilingualText("24 કલાક", "24 hrs"),
            idealFor = listOf(
                BilingualText("ઇન્સ્ટાગ્રામ ક્રિએટર્સ", "Creators & Influencers"),
                BilingualText("બિઝનેસ ઓનર્સ", "Founders & Coaches"),
                BilingualText("ઇવેન્ટ હાઇલાઇટ્સ", "Event & Product Clips")
            ),
            deliverables = listOf(
                BilingualText("બીટ-સિન્ક ટ્રાન્ઝિશન અને કેપ્શન્સ", "Beat-synced cuts & kinetic text"),
                BilingualText("AI નોઇઝ રિમૂવલ અને કલર", "Audio cleanup & color grading"),
                BilingualText("ફાસ્ટ ટર્નઅરાઉન્ડ રિવ્યુ", "1 complimentary revision round")
            )
        )
    )

    val showcases: List<ShowcaseItem> = listOf(
        ShowcaseItem(
            id = "sc_ai_model",
            serviceId = "ai_model_ad",
            title = BilingualText("રોયલ બુટિક AI મોડેલ રીલ", "Royal Boutique AI Model Reel"),
            tag = BilingualText("AI મોડેલ એડ", "AI Model Ad"),
            formatBadge = "9:16 Reels",
            durationLabel = "0:25",
            imageRes = R.drawable.img_showcase_ai_model_1794846195341,
            summary = BilingualText(
                gu = "ફક્ત પ્રોડક્ટના ફોટો પરથી બનાવેલ વર્ચ્યુઅલ ફેશન મોડેલ કેમ્પેઇન — શૂટિંગ ખર્ચ વગર 4x વધુ રીચ.",
                en = "Virtual fashion lookbook commercial generated from flat-lay garment photos with warm rim lighting and silk motion."
            ),
            sampleVoiceover = BilingualText(
                gu = "આ ફેસ્ટિવ સિઝનમાં તમારી સ્ટાઇલને આપો રોયલ ટચ! નવું ડિઝાઇનર કલેક્શન હવે ઉપલબ્ધ છે.",
                en = "Give your festive wardrobe a royal statement. Discover our new signature collection today."
            ),
            shots = listOf(
                StoryboardShot(
                    sceneNumber = 1,
                    durationLabel = "0:00 - 0:07",
                    visualDirection = BilingualText("ગોલ્ડન સ્પોટલાઇટમાં AI મોડેલની સ્લો-મોશન એન્ટ્રી.", "Slow-motion AI model entrance bathed in golden rim light."),
                    cameraNote = BilingualText("ક્લોઝ-અપ પોર્ટ્રેટ ઝૂમ", "85mm portrait push-in")
                ),
                StoryboardShot(
                    sceneNumber = 2,
                    durationLabel = "0:07 - 0:17",
                    visualDirection = BilingualText("ફેબ્રિક ડિટેલ અને જ્વેલરી વર્કનો મેક્રો શોટ.", "Macro sweep highlighting embroidery and gold detailing."),
                    cameraNote = BilingualText("સ્મૂથ પેન + બીટ કટ", "Beat-synced lateral glide")
                ),
                StoryboardShot(
                    sceneNumber = 3,
                    durationLabel = "0:17 - 0:25",
                    visualDirection = BilingualText("શોપ લોગો, ઓફર બેજ અને WhatsApp CTA કાર્ડ.", "Brand logo lockup with festive offer & WhatsApp CTA."),
                    cameraNote = BilingualText("કિનેટિક ટેક્સ્ટ રિવીલ", "Kinetic gold end-card")
                )
            )
        ),
        ShowcaseItem(
            id = "sc_product",
            serviceId = "product_ad_video",
            title = BilingualText("લક્ઝરી વોચ અને પરફ્યુમ 3D એડ", "Luxury Watch & Fragrance 3D Ad"),
            tag = BilingualText("પ્રોડક્ટ વીડિયો", "Product Video"),
            formatBadge = "4K Commercial",
            durationLabel = "0:20",
            imageRes = R.drawable.img_showcase_product_ad_1794846194601,
            summary = BilingualText(
                gu = "ડાર્ક ઓબ્સિડિયન સ્ટેજ પર વોટર ડ્રોપ્સ અને ગોલ્ડન લાઇટ રેઝ સાથે પ્રીમિયમ પ્રોડક્ટ લોન્ચ રીલ.",
                en = "High-contrast obsidian pedestal reveal featuring glistening droplets, amber light shafts, and precision macro transitions."
            ),
            sampleVoiceover = BilingualText(
                gu = "દરેક સેકન્ડમાં ક્લાસ, દરેક અંદાજમાં રોયલ્ટી — હવે તમારા હાથમાં.",
                en = "Precision engineered for presence. Elevate every moment with pure craftsmanship."
            ),
            shots = listOf(
                StoryboardShot(
                    sceneNumber = 1,
                    durationLabel = "0:00 - 0:06",
                    visualDirection = BilingualText("અંધારામાંથી ગોલ્ડન લાઇટ બીમ સાથે પ્રોડક્ટનું આગમન.", "Product emerges from shadow into crisp amber light rays."),
                    cameraNote = BilingualText("લો-એંગલ રિવ્યુલ", "Low-angle dramatic tilt")
                ),
                StoryboardShot(
                    sceneNumber = 2,
                    durationLabel = "0:06 - 0:14",
                    visualDirection = BilingualText("વોટર સ્પ્લેશ અને ડાયલ/બોટલનું સ્લો-મોશન ફોકસ.", "Micro water droplets glistening across glass & metal."),
                    cameraNote = BilingualText("120fps મેક્રો ઓર્બિટ", "Macro 360° orbit")
                ),
                StoryboardShot(
                    sceneNumber = 3,
                    durationLabel = "0:14 - 0:20",
                    visualDirection = BilingualText("કિંમત, સ્પેશિયલ ઓફર અને ઓર્ડર લિંક.", "Price tag pulse and direct WhatsApp order badge."),
                    cameraNote = BilingualText("ક્લીન સ્લાઇડ-અપ", "Minimalist end-slate")
                )
            )
        ),
        ShowcaseItem(
            id = "sc_shop",
            serviceId = "shop_ad_video",
            title = BilingualText("શોરૂમ ફેસ્ટિવ કેમ્પેઇન અને પોસ્ટર્સ", "Showroom Festive Reel & Posters"),
            tag = BilingualText("શોપ એડ અને પોસ્ટર", "Shop Ad & Poster"),
            formatBadge = "Reel + Poster",
            durationLabel = "0:30",
            imageRes = R.drawable.img_showcase_poster_shop_1794846196168,
            summary = BilingualText(
                gu = "તમારા શોરૂમની વિઝિટ વધારવા માટે સિનેમેટિક ટૂર વીડિયો અને મેચિંગ સોશિયલ મીડિયા પોસ્ટર્સ.",
                en = "Coordinated showroom walk-through promo paired with matching festive offer posters for Instagram and WhatsApp broadcasts."
            ),
            sampleVoiceover = BilingualText(
                gu = "તમારા શહેરનો વિશ્વાસુ શોરૂમ! આ અઠવાડિયે ખાસ ડિસ્કાઉન્ટનો લાભ લો — આજે જ મુલાકાત લો.",
                en = "Your city's trusted destination for signature style. Visit our showroom this week for exclusive offers."
            ),
            shots = listOf(
                StoryboardShot(
                    sceneNumber = 1,
                    durationLabel = "0:00 - 0:10",
                    visualDirection = BilingualText("શોરૂમના ગ્લાસ ફ્રન્ટ અને લાઇટિંગનો ભવ્ય શોટ.", "Warm architectural glide through illuminated showroom entrance."),
                    cameraNote = BilingualText("વાઇડ ગિમ્બલ શોટ", "Wide stabilizer glide")
                ),
                StoryboardShot(
                    sceneNumber = 2,
                    durationLabel = "0:10 - 0:22",
                    visualDirection = BilingualText("ટોપ કલેક્શન અને ડિસ્કાઉન્ટ બેનરનું ફાસ્ટ મોન્ટાજ.", "Curated rack highlights with crisp discount callouts."),
                    cameraNote = BilingualText("ડાયનેમિક સ્પીડ રેમ્પ", "Speed-ramp montage")
                ),
                StoryboardShot(
                    sceneNumber = 3,
                    durationLabel = "0:22 - 0:30",
                    visualDirection = BilingualText("એડ્રેસ, ગૂગલ મેપ લોકેશન અને કોલ નંબર.", "Store address card, hours, and WhatsApp inquiry button."),
                    cameraNote = BilingualText("ક્લીન ટાઇપોગ્રાફી કાર્ડ", "Branded location slate")
                )
            )
        ),
        ShowcaseItem(
            id = "sc_cinema",
            serviceId = "cinematic_ad_videos",
            title = BilingualText("સિનેમેટિક બ્રાન્ડ ફિલ્મ અને વોઇસઓવર", "Cinematic Brand Film & Voiceover"),
            tag = BilingualText("સિનેમેટિક એડ", "Cinematic Ad"),
            formatBadge = "16:9 Cinema",
            durationLabel = "0:45",
            imageRes = R.drawable.img_hero_studio_1794846194898,
            summary = BilingualText(
                gu = "ગુજરાતી સ્ટુડિયો વોઇસઓવર, બેકગ્રાઉન્ડ સ્કોર અને મૂવી-ગ્રેડ AI વિઝ્યુઅલ્સ સાથેની બ્રાન્ડ ફિલ્મ.",
                en = "Full-production commercial film combining studio voice narration, custom sound design, and dramatic AI stage visuals."
            ),
            sampleVoiceover = BilingualText(
                gu = "દરેક સપનાની શરૂઆત એક વિચારથી થાય છે — Flickora AI Studio સાથે તમારી બ્રાન્ડને બનાવો અવિસ્મરણીય.",
                en = "Every iconic brand starts with a bold story. Make yours unforgettable with Flickora AI Studio."
            ),
            shots = listOf(
                StoryboardShot(
                    sceneNumber = 1,
                    durationLabel = "0:00 - 0:12",
                    visualDirection = BilingualText("સ્ટુડિયો સ્પોટલાઇટ્સ ઓન થતાં બ્રાન્ડનું રહસ્યમય ઓપનિંગ.", "Studio spotlights ignite in sequence revealing the hero stage."),
                    cameraNote = BilingualText("સિનેમેટિક એનામોર્ફિક ફ્લેર", "Anamorphic lens sweep")
                ),
                StoryboardShot(
                    sceneNumber = 2,
                    durationLabel = "0:12 - 0:30",
                    visualDirection = BilingualText("ગ્રાહકની વાર્તા અને પ્રોડક્ટનો ભાવનાત્મક સંગમ.", "Story-driven montage blending brand values and visual polish."),
                    cameraNote = BilingualText("ડીપ ફિલ્મ ગ્રેડ", "24fps cinema grade")
                ),
                StoryboardShot(
                    sceneNumber = 3,
                    durationLabel = "0:30 - 0:45",
                    visualDirection = BilingualText("વોઇસઓવર ક્રાઉન લાઇન અને Create • Edit • Inspire લોગો.", "Signature voiceover sign-off with animated brand emblem."),
                    cameraNote = BilingualText("ફેડ-ટુ-ગોલ્ડ એન્ડિંગ", "Fade to gold lockup")
                )
            )
        )
    )
}
