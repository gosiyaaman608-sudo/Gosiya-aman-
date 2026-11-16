package com.example.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.StudioCatalog
import com.example.model.AppLanguage
import com.example.model.ServiceIconType
import com.example.model.ShowcaseItem
import com.example.model.StudioService
import com.example.ui.theme.AmberGlow
import com.example.ui.theme.CinemaGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PureSilk
import com.example.ui.theme.RoseCoral
import com.example.ui.theme.VelvetElevated
import com.example.ui.theme.VelvetStroke
import com.example.ui.theme.VelvetSurface
import com.example.ui.theme.WhatsAppContainer
import com.example.ui.theme.WhatsAppEmerald

@Composable
fun FlickoraBrandEmblem(
    modifier: Modifier = Modifier
) {
    val cd = stringResource(R.string.cd_brand_emblem)
    Canvas(
        modifier = modifier
            .size(38.dp)
            .semantics { contentDescription = cd }
    ) {
        val radius = size.minDimension / 2f
        val center = Offset(size.width / 2f, size.height / 2f)

        drawCircle(
            brush = Brush.linearGradient(listOf(CinemaGold, AmberGlow)),
            radius = radius - 2.dp.toPx(),
            center = center,
            style = Stroke(width = 2.6.dp.toPx(), cap = StrokeCap.Round)
        )

        drawCircle(
            color = ElectricViolet.copy(alpha = 0.22f),
            radius = radius * 0.72f,
            center = center
        )

        val playPath = Path().apply {
            val s = radius * 0.44f
            moveTo(center.x - s * 0.55f, center.y - s * 0.8f)
            lineTo(center.x + s * 0.95f, center.y)
            lineTo(center.x - s * 0.55f, center.y + s * 0.8f)
            close()
        }
        drawPath(
            path = playPath,
            brush = Brush.linearGradient(listOf(CinemaGold, RoseCoral))
        )
    }
}

@Composable
fun FlickoraTopBar(
    language: AppLanguage,
    onToggleLanguage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = ObsidianBg.copy(alpha = 0.96f),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                FlickoraBrandEmblem()
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = StudioCatalog.STUDIO_NAME,
                        style = MaterialTheme.typography.titleLarge,
                        color = PureSilk,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = StudioCatalog.STUDIO_TAGLINE,
                        style = MaterialTheme.typography.labelMedium,
                        color = CinemaGold,
                        maxLines = 1
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(50),
                color = VelvetElevated,
                border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.45f)),
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .clip(RoundedCornerShape(50))
                    .clickable(onClick = onToggleLanguage)
                    .testTag("language_toggle_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Translate,
                        contentDescription = stringResource(R.string.cd_toggle_language),
                        tint = CinemaGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "ગુજ / EN" else "EN / ગુજ",
                        style = MaterialTheme.typography.labelLarge,
                        color = PureSilk
                    )
                }
            }
        }
    }
}

@Composable
fun SampleAiNoticeBanner(
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = ElectricViolet.copy(alpha = 0.14f),
        border = BorderStroke(1.dp, ElectricViolet.copy(alpha = 0.4f)),
        modifier = modifier
            .fillMaxWidth()
            .testTag("sample_ai_notice_banner")
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = stringResource(R.string.sample_ai_notice_title),
                tint = ElectricViolet,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "સેમ્પલ સ્ટુડિયો મોડ (Sample Mode)"
                    } else {
                        stringResource(R.string.sample_ai_notice_title)
                    },
                    style = MaterialTheme.typography.labelLarge,
                    color = PureSilk
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        stringResource(R.string.sample_ai_notice_body_gu)
                    } else {
                        stringResource(R.string.sample_ai_notice_body)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

fun serviceIconFor(iconType: ServiceIconType): ImageVector = when (iconType) {
    ServiceIconType.AI_MODEL -> Icons.Filled.AutoAwesome
    ServiceIconType.PRODUCT_VIDEO -> Icons.Filled.CameraAlt
    ServiceIconType.SHOP_VIDEO -> Icons.Filled.Storefront
    ServiceIconType.AI_POSTER -> Icons.Filled.Palette
    ServiceIconType.CINEMATIC_AD -> Icons.Filled.MovieCreation
    ServiceIconType.VOICEOVER -> Icons.Filled.GraphicEq
    ServiceIconType.VIDEO_EDITING -> Icons.Filled.Videocam
}

fun serviceAccentFor(iconType: ServiceIconType): Color = when (iconType) {
    ServiceIconType.AI_MODEL -> RoseCoral
    ServiceIconType.PRODUCT_VIDEO -> CinemaGold
    ServiceIconType.SHOP_VIDEO -> WhatsAppEmerald
    ServiceIconType.AI_POSTER -> ElectricViolet
    ServiceIconType.CINEMATIC_AD -> AmberGlow
    ServiceIconType.VOICEOVER -> ElectricViolet
    ServiceIconType.VIDEO_EDITING -> RoseCoral
}

@Composable
fun WhatsAppButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    testTag: String = "whatsapp_cta_button"
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = WhatsAppEmerald,
            contentColor = ObsidianBg
        ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        modifier = modifier
            .minimumInteractiveComponentSize()
            .testTag(testTag)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = stringResource(R.string.cd_whatsapp_icon),
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseDetailSheet(
    showcase: ShowcaseItem,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onRequestSimilarOnWhatsApp: (ShowcaseItem) -> Unit,
    onOpenInDemoLab: (String) -> Unit
) {
    BackHandler(onBack = onDismiss)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = VelvetSurface,
        contentColor = PureSilk
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = CinemaGold.copy(alpha = 0.16f),
                    border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.45f))
                ) {
                    Text(
                        text = "${showcase.tag.resolve(language)} • ${showcase.formatBadge}",
                        style = MaterialTheme.typography.labelMedium,
                        color = AmberGlow,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier.testTag("close_showcase_sheet")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cd_close_sheet),
                        tint = PureSilk
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .clip(RoundedCornerShape(18.dp))
            ) {
                Image(
                    painter = painterResource(id = showcase.imageRes),
                    contentDescription = showcase.title.resolve(language),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, ObsidianBg.copy(alpha = 0.85f))
                            )
                        )
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(CinemaGold),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = ObsidianBg
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = showcase.title.resolve(language),
                            style = MaterialTheme.typography.titleMedium,
                            color = PureSilk
                        )
                        Text(
                            text = "${showcase.durationLabel} • Flickora Production Breakdown",
                            style = MaterialTheme.typography.labelMedium,
                            color = AmberGlow
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = showcase.summary.resolve(language),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = if (language == AppLanguage.GUJARATI) {
                    "સેમ્પલ વોઇસઓવર સ્ક્રિપ્ટ"
                } else {
                    "Sample Studio Voiceover"
                },
                style = MaterialTheme.typography.titleSmall,
                color = CinemaGold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Surface(
                shape = RoundedCornerShape(14.dp),
                color = VelvetElevated,
                border = BorderStroke(1.dp, VelvetStroke),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "“${showcase.sampleVoiceover.resolve(language)}”",
                    style = MaterialTheme.typography.bodyLarge,
                    color = PureSilk,
                    modifier = Modifier.padding(14.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = if (language == AppLanguage.GUJARATI) {
                    "3-સીન સ્ટોરીબોર્ડ બ્રેકડાઉન"
                } else {
                    "3-Scene Production Storyboard"
                },
                style = MaterialTheme.typography.titleSmall,
                color = CinemaGold
            )
            Spacer(modifier = Modifier.height(8.dp))

            showcase.shots.forEach { shot ->
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = ObsidianBg.copy(alpha = 0.65f),
                    border = BorderStroke(1.dp, VelvetStroke),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Box(
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .background(ElectricViolet.copy(alpha = 0.25f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${shot.sceneNumber}",
                                style = MaterialTheme.typography.labelLarge,
                                color = PureSilk
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "${shot.durationLabel} • ${shot.cameraNote.resolve(language)}",
                                style = MaterialTheme.typography.labelMedium,
                                color = AmberGlow
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = shot.visualDirection.resolve(language),
                                style = MaterialTheme.typography.bodyMedium,
                                color = PureSilk
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            WhatsAppButton(
                label = if (language == AppLanguage.GUJARATI) {
                    "આવો ડેમો WhatsApp પર મંગાવો"
                } else {
                    "Request Similar Demo on WhatsApp"
                },
                onClick = { onRequestSimilarOnWhatsApp(showcase) },
                modifier = Modifier.fillMaxWidth(),
                testTag = "showcase_whatsapp_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedButton(
                onClick = { onOpenInDemoLab(showcase.serviceId) },
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .minimumInteractiveComponentSize()
                    .testTag("showcase_customize_demo_button")
            ) {
                Icon(
                    imageVector = Icons.Filled.AutoAwesome,
                    contentDescription = null,
                    tint = CinemaGold,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "તમારા બિઝનેસ માટે કોન્સેપ્ટ બનાવો"
                    } else {
                        "Customize Concept for My Business"
                    },
                    color = PureSilk
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailSheet(
    service: StudioService,
    language: AppLanguage,
    isInBrief: Boolean,
    onDismiss: () -> Unit,
    onToggleBrief: (String) -> Unit,
    onBookDemo: (String) -> Unit
) {
    BackHandler(onBack = onDismiss)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val accent = serviceAccentFor(service.iconType)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = VelvetSurface,
        contentColor = PureSilk
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
                .padding(bottom = 28.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(accent.copy(alpha = 0.16f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = serviceIconFor(service.iconType),
                            contentDescription = null,
                            tint = accent
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = service.title.resolve(language),
                            style = MaterialTheme.typography.titleLarge,
                            color = PureSilk
                        )
                        Text(
                            text = "⏱ ${service.turnaround.resolve(language)}",
                            style = MaterialTheme.typography.labelMedium,
                            color = AmberGlow
                        )
                    }
                }
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.cd_close_sheet),
                        tint = PureSilk
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = service.description.resolve(language),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = if (language == AppLanguage.GUJARATI) "આ બિઝનેસ માટે ઉત્તમ" else "Ideal For",
                style = MaterialTheme.typography.titleSmall,
                color = CinemaGold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                service.idealFor.forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = VelvetElevated,
                        border = BorderStroke(1.dp, VelvetStroke)
                    ) {
                        Text(
                            text = tag.resolve(language),
                            style = MaterialTheme.typography.labelMedium,
                            color = PureSilk,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = if (language == AppLanguage.GUJARATI) "તમને શું મળશે" else "What You Receive",
                style = MaterialTheme.typography.titleSmall,
                color = CinemaGold
            )
            Spacer(modifier = Modifier.height(8.dp))
            service.deliverables.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = WhatsAppEmerald,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = item.resolve(language),
                        style = MaterialTheme.typography.bodyMedium,
                        color = PureSilk
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = { onToggleBrief(service.id) },
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp,
                        if (isInBrief) WhatsAppEmerald else VelvetStroke
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("sheet_toggle_brief_button")
                ) {
                    Text(
                        text = if (isInBrief) {
                            if (language == AppLanguage.GUJARATI) "ઉમેરાયેલ ✓" else "Added ✓"
                        } else {
                            if (language == AppLanguage.GUJARATI) "+ બ્રીફમાં ઉમેરો" else "+ Add to Brief"
                        },
                        color = if (isInBrief) WhatsAppEmerald else PureSilk,
                        maxLines = 1
                    )
                }

                Button(
                    onClick = { onBookDemo(service.id) },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CinemaGold,
                        contentColor = ObsidianBg
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("sheet_book_demo_button")
                ) {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "ડેમો બુક કરો" else "Book Demo",
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
