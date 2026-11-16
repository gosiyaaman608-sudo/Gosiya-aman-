package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.StudioCatalog
import com.example.model.AdConceptDemo
import com.example.model.AppLanguage
import com.example.model.BusinessCategory
import com.example.model.ScriptLanguage
import com.example.ui.ConceptGenerationState
import com.example.ui.StudioUiState
import com.example.ui.theme.AmberGlow
import com.example.ui.theme.CinemaGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.ErrorCrimson
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PureSilk
import com.example.ui.theme.RoseCoral
import com.example.ui.theme.VelvetElevated
import com.example.ui.theme.VelvetStroke
import com.example.ui.theme.VelvetSurface
import com.example.ui.theme.WhatsAppEmerald
import com.example.util.IntentHelpers

@Composable
fun DemoLabScreen(
    state: StudioUiState,
    onBusinessNameChange: (String) -> Unit,
    onOfferChange: (String) -> Unit,
    onCategorySelect: (BusinessCategory) -> Unit,
    onServiceSelect: (String) -> Unit,
    onScriptLanguageSelect: (ScriptLanguage) -> Unit,
    onGenerateConcept: () -> Unit,
    onSendToBooking: (AdConceptDemo) -> Unit,
    modifier: Modifier = Modifier
) {
    val language = state.language
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("demo_lab_screen"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "AI એડ ડેમો કોન્સેપ્ટ લેબ ✨"
                    } else {
                        "AI Ad Concept Demo Lab ✨"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = PureSilk
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "તમારા બિઝનેસ, શોપ કે પ્રોડક્ટની વિગત નાખો અને Gemini AI દ્વારા તુરંત કસ્ટમ જાહેરાત સ્ક્રિપ્ટ અને સ્ટોરીબોર્ડ બનાવો."
                    } else {
                        "Enter your business or product details to generate a custom ad hook, voiceover script, and visual storyboard with Gemini AI."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = VelvetSurface,
                border = BorderStroke(1.dp, VelvetStroke),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    val fieldColors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = CinemaGold,
                        unfocusedBorderColor = VelvetStroke,
                        focusedLabelColor = CinemaGold,
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        cursorColor = CinemaGold,
                        focusedTextColor = PureSilk,
                        unfocusedTextColor = PureSilk
                    )

                    OutlinedTextField(
                        value = state.demoBusinessName,
                        onValueChange = onBusinessNameChange,
                        label = {
                            Text(
                                if (language == AppLanguage.GUJARATI) {
                                    "બિઝનેસ / શોપ / પ્રોડક્ટનું નામ"
                                } else {
                                    "Business / Shop / Product Name"
                                }
                            )
                        },
                        placeholder = {
                            Text(
                                if (language == AppLanguage.GUJARATI) {
                                    "દા.ત. શગુન બુટિક / સ્ટાર વોચ"
                                } else {
                                    "e.g. Shagun Boutique / Aura Perfumes"
                                }
                            )
                        },
                        singleLine = true,
                        colors = fieldColors,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("demo_business_name_input")
                    )

                    OutlinedTextField(
                        value = state.demoOfferHighlight,
                        onValueChange = onOfferChange,
                        label = {
                            Text(
                                if (language == AppLanguage.GUJARATI) {
                                    "ખાસ ઓફર અથવા લોન્ચ વિગત (વૈકલ્પિક)"
                                } else {
                                    "Special Offer or Launch Highlight (Optional)"
                                }
                            )
                        },
                        placeholder = {
                            Text(
                                if (language == AppLanguage.GUJARATI) {
                                    "દા.ત. નવું કલેક્શન • 20% ડિસ્કાઉન્ટ"
                                } else {
                                    "e.g. Grand Opening 20% Off"
                                }
                            )
                        },
                        singleLine = true,
                        colors = fieldColors,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("demo_offer_input")
                    )

                    Column {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "બિઝનેસ કેટેગરી" else "Business Category",
                            style = MaterialTheme.typography.titleSmall,
                            color = AmberGlow
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            BusinessCategory.entries.forEach { category ->
                                val selected = category == state.demoCategory
                                FilterChip(
                                    selected = selected,
                                    onClick = { onCategorySelect(category) },
                                    label = { Text(category.label.resolve(language)) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = CinemaGold,
                                        selectedLabelColor = ObsidianBg,
                                        containerColor = VelvetElevated,
                                        labelColor = PureSilk
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        enabled = true,
                                        selected = selected,
                                        borderColor = VelvetStroke,
                                        selectedBorderColor = CinemaGold
                                    )
                                )
                            }
                        }
                    }

                    Column {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "એડ સર્વિસ પ્રકાર" else "Ad Format / Service",
                            style = MaterialTheme.typography.titleSmall,
                            color = AmberGlow
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            StudioCatalog.services.forEach { service ->
                                val selected = service.id == state.demoServiceId
                                FilterChip(
                                    selected = selected,
                                    onClick = { onServiceSelect(service.id) },
                                    label = { Text(service.title.resolve(language)) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = ElectricViolet,
                                        selectedLabelColor = PureSilk,
                                        containerColor = VelvetElevated,
                                        labelColor = PureSilk
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        enabled = true,
                                        selected = selected,
                                        borderColor = VelvetStroke,
                                        selectedBorderColor = ElectricViolet
                                    )
                                )
                            }
                        }
                    }

                    Column {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "વોઇસઓવર અને સ્ક્રિપ્ટ ભાષા" else "Script Language",
                            style = MaterialTheme.typography.titleSmall,
                            color = AmberGlow
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            ScriptLanguage.entries.forEach { scriptLang ->
                                val selected = scriptLang == state.demoScriptLanguage
                                FilterChip(
                                    selected = selected,
                                    onClick = { onScriptLanguageSelect(scriptLang) },
                                    label = { Text(scriptLang.label.resolve(language)) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = RoseCoral,
                                        selectedLabelColor = PureSilk,
                                        containerColor = VelvetElevated,
                                        labelColor = PureSilk
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        enabled = true,
                                        selected = selected,
                                        borderColor = VelvetStroke,
                                        selectedBorderColor = RoseCoral
                                    )
                                )
                            }
                        }
                    }

                    Button(
                        onClick = onGenerateConcept,
                        enabled = state.conceptState !is ConceptGenerationState.Loading,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CinemaGold,
                            contentColor = ObsidianBg
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("generate_demo_concept_button")
                    ) {
                        if (state.conceptState is ConceptGenerationState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = ObsidianBg,
                                strokeWidth = 2.5.dp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = if (language == AppLanguage.GUJARATI) "તૈયાર થઈ રહ્યું છે..." else "Preparing Demo...",
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.AutoAwesome,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.GUJARATI) {
                                    "AI ડેમો કોન્સેપ્ટ બનાવો"
                                } else {
                                    "Generate AI Demo Concept"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        when (val conceptState = state.conceptState) {
            is ConceptGenerationState.Idle -> {
                item {
                    Surface(
                        shape = RoundedCornerShape(18.dp),
                        color = VelvetSurface,
                        border = BorderStroke(1.dp, VelvetStroke),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.MovieCreation,
                                contentDescription = null,
                                tint = CinemaGold,
                                modifier = Modifier.size(34.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (language == AppLanguage.GUJARATI) {
                                    "તમારો કસ્ટમ એડ કોન્સેપ્ટ અહીં દેખાશે"
                                } else {
                                    "Your Custom Ad Concept Will Appear Here"
                                },
                                style = MaterialTheme.typography.titleMedium,
                                color = PureSilk
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (language == AppLanguage.GUJARATI) {
                                    "ઉપર બિઝનેસનું નામ લખી 'સેમ્પલ ડેમો કોન્સેપ્ટ બનાવો' પર ટેપ કરો."
                                } else {
                                    "Enter your business name above and tap Generate to preview a sample script and storyboard."
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            is ConceptGenerationState.Loading -> Unit

            is ConceptGenerationState.Error -> {
                item {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = ErrorCrimson.copy(alpha = 0.14f),
                        border = BorderStroke(1.dp, ErrorCrimson.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("demo_error_card")
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                text = conceptState.message,
                                style = MaterialTheme.typography.bodyMedium,
                                color = ErrorCrimson
                            )
                            OutlinedButton(
                                onClick = onGenerateConcept,
                                shape = RoundedCornerShape(12.dp),
                                border = BorderStroke(1.dp, ErrorCrimson.copy(alpha = 0.7f)),
                                modifier = Modifier.minimumInteractiveComponentSize()
                            ) {
                                Text(
                                    text = if (language == AppLanguage.GUJARATI) "ફરી પ્રયાસ કરો" else "Try Again",
                                    color = PureSilk
                                )
                            }
                        }
                    }
                }
            }

            is ConceptGenerationState.Success -> {
                item {
                    GeneratedConceptCard(
                        demo = conceptState.demo,
                        language = language,
                        onCopyScript = {
                            val fullText = buildString {
                                appendLine(conceptState.demo.hookHeadline)
                                appendLine()
                                appendLine(conceptState.demo.voiceoverScript)
                            }
                            IntentHelpers.copyToClipboard(context, "Flickora Demo Script", fullText)
                        },
                        onSendToBooking = { onSendToBooking(conceptState.demo) },
                        onDirectWhatsApp = {
                            val msg = buildString {
                                appendLine("નમસ્તે Flickora AI Studio! 🎬")
                                appendLine("મારા બિઝનેસ '${conceptState.demo.businessName}' માટે મને આ પ્રકારનો ડેમો વિડિયો/પોસ્ટર બનાવવો છે:")
                                appendLine("Service: ${conceptState.demo.serviceTitle.en}")
                                appendLine("Headline: ${conceptState.demo.hookHeadline}")
                                append("કૃપા કરીને વિગત અને નાનો ડેમો શેર કરો.")
                            }
                            IntentHelpers.openWhatsApp(
                                context,
                                StudioCatalog.whatsappDesks.first().number,
                                msg
                            )
                        }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GeneratedConceptCard(
    demo: AdConceptDemo,
    language: AppLanguage,
    onCopyScript: () -> Unit,
    onSendToBooking: () -> Unit,
    onDirectWhatsApp: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = VelvetElevated,
        border = BorderStroke(1.5.dp, CinemaGold.copy(alpha = 0.55f)),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("generated_demo_concept_card")
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = ElectricViolet.copy(alpha = 0.22f),
                    border = BorderStroke(1.dp, ElectricViolet.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = if (demo.isSampleMode) {
                            if (language == AppLanguage.GUJARATI) "સેમ્પલ સ્ટુડિયો કોન્સેપ્ટ" else "Sample Studio Concept"
                        } else {
                            if (language == AppLanguage.GUJARATI) "Gemini AI કોન્સેપ્ટ ✨" else "Gemini AI Concept ✨"
                        },
                        style = MaterialTheme.typography.labelMedium,
                        color = PureSilk,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }

                Text(
                    text = demo.serviceTitle.resolve(language),
                    style = MaterialTheme.typography.labelMedium,
                    color = AmberGlow
                )
            }

            Column {
                Text(
                    text = demo.hookHeadline,
                    style = MaterialTheme.typography.titleLarge,
                    color = PureSilk
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = demo.adTagline,
                    style = MaterialTheme.typography.labelMedium,
                    color = CinemaGold
                )
            }

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = ObsidianBg.copy(alpha = 0.7f),
                border = BorderStroke(1.dp, VelvetStroke),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.GraphicEq,
                                contentDescription = null,
                                tint = CinemaGold,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.GUJARATI) "વોઇસઓવર સ્ક્રિપ્ટ" else "Voiceover Script",
                                style = MaterialTheme.typography.titleSmall,
                                color = CinemaGold
                            )
                        }
                        IconButton(
                            onClick = onCopyScript,
                            modifier = Modifier.testTag("copy_demo_script_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ContentCopy,
                                contentDescription = "Copy Script",
                                tint = PureSilk,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = demo.voiceoverScript,
                        style = MaterialTheme.typography.bodyLarge,
                        color = PureSilk
                    )
                }
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.MovieCreation,
                        contentDescription = null,
                        tint = RoseCoral,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "વિઝ્યુઅલ સ્ટોરીબોર્ડ" else "Visual Storyboard",
                        style = MaterialTheme.typography.titleSmall,
                        color = RoseCoral
                    )
                }
                demo.scenes.forEach { scene ->
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = VelvetSurface,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = scene,
                            style = MaterialTheme.typography.bodyMedium,
                            color = PureSilk,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
            }

            Row(verticalAlignment = Alignment.Top) {
                Icon(
                    imageVector = Icons.Filled.Palette,
                    contentDescription = null,
                    tint = ElectricViolet,
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "પોસ્ટર આર્ટ ડિરેક્શન" else "Poster Art Direction",
                        style = MaterialTheme.typography.titleSmall,
                        color = ElectricViolet
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = demo.posterConcept,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                demo.hashtags.forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = VelvetSurface
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            color = AmberGlow,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }

            Button(
                onClick = onDirectWhatsApp,
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppEmerald,
                    contentColor = ObsidianBg
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("demo_send_whatsapp_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "આ ડેમો WhatsApp પર મંગાવો"
                    } else {
                        "Send Concept to WhatsApp"
                    },
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedButton(
                onClick = onSendToBooking,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("demo_open_booking_button")
            ) {
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "કસ્ટમ બ્રીફ ફોર્મમાં ઉમેરો"
                    } else {
                        "Customize in Booking Form"
                    },
                    color = PureSilk
                )
            }
        }
    }
}
