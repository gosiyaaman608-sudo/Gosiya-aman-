package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.StudioCatalog
import com.example.model.AppLanguage
import com.example.model.ShowcaseItem
import com.example.model.StudioService
import com.example.ui.components.WhatsAppButton
import com.example.ui.components.serviceAccentFor
import com.example.ui.components.serviceIconFor
import com.example.ui.theme.AmberGlow
import com.example.ui.theme.CinemaGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PureSilk
import com.example.ui.theme.RoseCoral
import com.example.ui.theme.VelvetElevated
import com.example.ui.theme.VelvetStroke
import com.example.ui.theme.VelvetSurface
import com.example.ui.theme.WhatsAppEmerald
import com.example.util.IntentHelpers

@Composable
fun HomeScreen(
    language: AppLanguage,
    onOpenShowcase: (ShowcaseItem) -> Unit,
    onOpenService: (StudioService) -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToDemoLab: () -> Unit,
    onNavigateToContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            HeroStudioCard(
                language = language,
                onTryDemoLab = onNavigateToDemoLab,
                onBookMiniDemo = onNavigateToContact
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MetricPillCard(
                    value = "7",
                    label = if (language == AppLanguage.GUJARATI) "AI ક્રિએટિવ સર્વિસ" else "AI Ad Services",
                    accent = CinemaGold,
                    modifier = Modifier.weight(1f)
                )
                MetricPillCard(
                    value = "24h",
                    label = if (language == AppLanguage.GUJARATI) "ઝડપી ડિલિવરી" else "Fast Turnaround",
                    accent = ElectricViolet,
                    modifier = Modifier.weight(1f)
                )
                MetricPillCard(
                    value = "Free",
                    label = if (language == AppLanguage.GUJARATI) "નાનો ડેમો" else "Mini Demo",
                    accent = WhatsAppEmerald,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            SectionHeader(
                title = if (language == AppLanguage.GUJARATI) {
                    "સેમ્પલ શોકેસ અને ડેમો રીલ્સ"
                } else {
                    "Featured Reels & AI Posters"
                },
                actionText = if (language == AppLanguage.GUJARATI) "ડેમો લેબ" else "Demo Lab",
                onAction = onNavigateToDemoLab
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(end = 4.dp)
            ) {
                items(StudioCatalog.showcases, key = { it.id }) { showcase ->
                    ShowcasePreviewCard(
                        item = showcase,
                        language = language,
                        onClick = { onOpenShowcase(showcase) }
                    )
                }
            }
        }

        item {
            SectionHeader(
                title = if (language == AppLanguage.GUJARATI) {
                    "અમારી પ્રીમિયમ AI સર્વિસ"
                } else {
                    "Our 7 Signature Services"
                },
                actionText = if (language == AppLanguage.GUJARATI) "બધી જુઓ" else "View All",
                onAction = onNavigateToServices
            )
        }

        items(StudioCatalog.services, key = { it.id }) { service ->
            CompactServiceRow(
                service = service,
                language = language,
                onClick = { onOpenService(service) }
            )
        }

        item {
            StudioQuickConnectCard(
                language = language,
                onWhatsAppPrimary = {
                    val msg = if (language == AppLanguage.GUJARATI) {
                        "નમસ્તે Flickora AI Studio! મારા બિઝનેસ માટે મને એક નાનો ડેમો જોવો છે."
                    } else {
                        "Hello Flickora AI Studio! I would like to see a mini demo for my business."
                    }
                    IntentHelpers.openWhatsApp(context, StudioCatalog.whatsappDesks.first().number, msg)
                },
                onOpenInstagram = {
                    IntentHelpers.openInstagram(context, StudioCatalog.INSTAGRAM_URL)
                },
                onOpenBooking = onNavigateToContact
            )
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun HeroStudioCard(
    language: AppLanguage,
    onTryDemoLab: () -> Unit,
    onBookMiniDemo: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(24.dp),
        color = VelvetSurface,
        border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.35f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_studio_1794846194898),
                    contentDescription = stringResource(R.string.cd_hero_studio),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    ObsidianBg.copy(alpha = 0.25f),
                                    ObsidianBg.copy(alpha = 0.92f)
                                )
                            )
                        )
                )

                Surface(
                    shape = RoundedCornerShape(50),
                    color = ObsidianBg.copy(alpha = 0.78f),
                    border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.55f)),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            tint = CinemaGold,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = StudioCatalog.STUDIO_TAGLINE,
                            style = MaterialTheme.typography.labelMedium,
                            color = AmberGlow
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) {
                            "નમસ્તે સર/મેડમ! 👋"
                        } else {
                            "Welcome to Flickora AI Studio 👋"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        color = AmberGlow
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (language == AppLanguage.GUJARATI) {
                            "બિઝનેસ, શોપ અને પ્રોડક્ટ માટે પ્રીમિયમ AI વીડિયો અને પોસ્ટર્સ"
                        } else {
                            "Premium AI Commercial Videos & Creative Posters for Your Brand"
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        color = PureSilk
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            listOf(VelvetSurface, VelvetElevated)
                        )
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "તમારા બિઝનેસ અને પ્રોડક્ટને એક અલગ અને પ્રીમિયમ લુક આપતી આકર્ષક જાહેરાતો બનાવવા માટે અમે તૈયાર છીએ. 🚀"
                    } else {
                        "Give your business, retail shop, or product a distinctive luxury look with AI-powered ads, voiceovers, and cinematic edits. 🚀"
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = onTryDemoLab,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = CinemaGold,
                            contentColor = ObsidianBg
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("hero_demo_lab_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "ડેમો બનાવો" else "Concept Lab",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }

                    OutlinedButton(
                        onClick = onBookMiniDemo,
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, WhatsAppEmerald.copy(alpha = 0.7f)),
                        modifier = Modifier
                            .weight(1f)
                            .minimumInteractiveComponentSize()
                            .testTag("hero_contact_button")
                    ) {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "નાનો ડેમો મેળવો" else "Get Mini Demo",
                            style = MaterialTheme.typography.labelLarge,
                            color = WhatsAppEmerald,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetricPillCard(
    value: String,
    label: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = VelvetSurface,
        border = BorderStroke(1.dp, VelvetStroke),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                color = accent,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    actionText: String,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = PureSilk,
            modifier = Modifier.weight(1f)
        )
        TextButton(onClick = onAction) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelLarge,
                color = CinemaGold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = CinemaGold,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ShowcasePreviewCard(
    item: ShowcaseItem,
    language: AppLanguage,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = VelvetSurface,
        border = BorderStroke(1.dp, VelvetStroke),
        modifier = Modifier
            .width(248.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .testTag("showcase_card_${item.id}")
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(148.dp)
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.title.resolve(language),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, ObsidianBg.copy(alpha = 0.85f))
                            )
                        )
                )

                Surface(
                    shape = RoundedCornerShape(50),
                    color = ObsidianBg.copy(alpha = 0.78f),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(10.dp)
                ) {
                    Text(
                        text = item.formatBadge,
                        style = MaterialTheme.typography.labelSmall,
                        color = AmberGlow,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(10.dp)
                        .size(34.dp)
                        .clip(CircleShape)
                        .background(CinemaGold),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = ObsidianBg,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = item.durationLabel,
                    style = MaterialTheme.typography.labelMedium,
                    color = PureSilk,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
            }

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = item.tag.resolve(language),
                    style = MaterialTheme.typography.labelMedium,
                    color = RoseCoral
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = item.title.resolve(language),
                    style = MaterialTheme.typography.titleMedium,
                    color = PureSilk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = item.summary.resolve(language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun CompactServiceRow(
    service: StudioService,
    language: AppLanguage,
    onClick: () -> Unit
) {
    val accent = serviceAccentFor(service.iconType)
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = VelvetSurface,
        border = BorderStroke(1.dp, VelvetStroke),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .testTag("home_service_item_${service.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    tint = accent,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "✨ ${service.title.resolve(language)}",
                    style = MaterialTheme.typography.titleSmall,
                    color = PureSilk,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = service.subtitle.resolve(language),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
private fun StudioQuickConnectCard(
    language: AppLanguage,
    onWhatsAppPrimary: () -> Unit,
    onOpenInstagram: () -> Unit,
    onOpenBooking: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(22.dp),
        color = VelvetElevated,
        border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.4f)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Verified,
                    contentDescription = null,
                    tint = CinemaGold,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "ફ્રી નાનો ડેમો જોવા માટે સંપર્ક કરો"
                    } else {
                        "Ready for Your Mini Demo?"
                    },
                    style = MaterialTheme.typography.titleMedium,
                    color = PureSilk
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = if (language == AppLanguage.GUJARATI) {
                    "📲 WhatsApp: 6355675109 • 9737526661\n📸 Instagram: @flickora_ai_studio"
                } else {
                    "📲 WhatsApp: 6355675109 • 9737526661\n📸 Instagram: @flickora_ai_studio"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = AmberGlow
            )

            Spacer(modifier = Modifier.height(14.dp))

            WhatsAppButton(
                label = if (language == AppLanguage.GUJARATI) {
                    "WhatsApp પર મેસેજ કરો"
                } else {
                    "Chat on WhatsApp Now"
                },
                onClick = onWhatsAppPrimary,
                modifier = Modifier.fillMaxWidth(),
                testTag = "home_whatsapp_button"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = onOpenInstagram,
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, RoseCoral.copy(alpha = 0.65f)),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("home_instagram_button")
                ) {
                    Text(
                        text = "@flickora_ai_studio",
                        style = MaterialTheme.typography.labelMedium,
                        color = RoseCoral,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                OutlinedButton(
                    onClick = onOpenBooking,
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, VelvetStroke),
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .testTag("home_custom_brief_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = null,
                        tint = PureSilk,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "કસ્ટમ બુકિંગ" else "Custom Brief",
                        style = MaterialTheme.typography.labelMedium,
                        color = PureSilk,
                        maxLines = 1
                    )
                }
            }
        }
    }
}
