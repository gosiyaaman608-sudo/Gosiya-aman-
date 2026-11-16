package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.data.StudioCatalog
import com.example.model.AppLanguage
import com.example.model.ServiceCategory
import com.example.model.StudioService
import com.example.ui.components.serviceAccentFor
import com.example.ui.components.serviceIconFor
import com.example.ui.theme.AmberGlow
import com.example.ui.theme.CinemaGold
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PureSilk
import com.example.ui.theme.VelvetElevated
import com.example.ui.theme.VelvetStroke
import com.example.ui.theme.VelvetSurface
import com.example.ui.theme.WhatsAppEmerald

@Composable
fun ServicesScreen(
    language: AppLanguage,
    activeFilter: ServiceCategory,
    selectedBriefIds: Set<String>,
    onSelectFilter: (ServiceCategory) -> Unit,
    onToggleBrief: (String) -> Unit,
    onOpenServiceDetail: (StudioService) -> Unit,
    onProceedToBriefBooking: () -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredServices = remember(activeFilter) {
        if (activeFilter == ServiceCategory.ALL) {
            StudioCatalog.services
        } else {
            StudioCatalog.services.filter { it.category == activeFilter }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag("services_screen")
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = if (selectedBriefIds.isNotEmpty()) 96.dp else 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Column {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) {
                            "અમારી 7 ક્રિએટિવ સર્વિસ 🎬"
                        } else {
                            "Creative AI Production Services 🎬"
                        },
                        style = MaterialTheme.typography.headlineSmall,
                        color = PureSilk
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (language == AppLanguage.GUJARATI) {
                            "તમારા બિઝનેસ માટે જરૂરી સર્વિસ પસંદ કરો અને સીધો ડેમો બ્રીફ મોકલો."
                        } else {
                            "Explore deliverables or add services to build your instant WhatsApp demo brief."
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ServiceCategory.entries.forEach { category ->
                        val selected = category == activeFilter
                        FilterChip(
                            selected = selected,
                            onClick = { onSelectFilter(category) },
                            label = {
                                Text(
                                    text = category.label.resolve(language),
                                    style = MaterialTheme.typography.labelLarge
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = CinemaGold,
                                selectedLabelColor = ObsidianBg,
                                containerColor = VelvetSurface,
                                labelColor = PureSilk
                            ),
                            border = FilterChipDefaults.filterChipBorder(
                                enabled = true,
                                selected = selected,
                                borderColor = VelvetStroke,
                                selectedBorderColor = CinemaGold
                            ),
                            modifier = Modifier.testTag("service_filter_${category.name}")
                        )
                    }
                }
            }

            items(filteredServices, key = { it.id }) { service ->
                val isSelected = selectedBriefIds.contains(service.id)
                ServiceCatalogCard(
                    service = service,
                    language = language,
                    isInBrief = isSelected,
                    onCardClick = { onOpenServiceDetail(service) },
                    onToggleBrief = { onToggleBrief(service.id) }
                )
            }
        }

        AnimatedVisibility(
            visible = selectedBriefIds.isNotEmpty(),
            enter = slideInVertically { it } + fadeIn(),
            exit = slideOutVertically { it } + fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = VelvetElevated,
                border = BorderStroke(1.dp, WhatsAppEmerald.copy(alpha = 0.7f)),
                tonalElevation = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) {
                                "${selectedBriefIds.size} સર્વિસ પસંદ કરેલ છે"
                            } else {
                                "${selectedBriefIds.size} Service(s) Selected"
                            },
                            style = MaterialTheme.typography.titleSmall,
                            color = PureSilk
                        )
                        Text(
                            text = if (language == AppLanguage.GUJARATI) {
                                "ડેમો બુકિંગ માટે તૈયાર"
                            } else {
                                "Ready for custom demo request"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = WhatsAppEmerald
                        )
                    }

                    Button(
                        onClick = onProceedToBriefBooking,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppEmerald,
                            contentColor = ObsidianBg
                        ),
                        modifier = Modifier
                            .minimumInteractiveComponentSize()
                            .testTag("services_proceed_brief_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "આગળ વધો" else "Book Demo",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ServiceCatalogCard(
    service: StudioService,
    language: AppLanguage,
    isInBrief: Boolean,
    onCardClick: () -> Unit,
    onToggleBrief: () -> Unit
) {
    val accent = serviceAccentFor(service.iconType)

    Surface(
        shape = RoundedCornerShape(20.dp),
        color = VelvetSurface,
        border = BorderStroke(
            width = if (isInBrief) 1.5.dp else 1.dp,
            color = if (isInBrief) WhatsAppEmerald.copy(alpha = 0.7f) else VelvetStroke
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onCardClick)
            .testTag("service_card_${service.id}")
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(accent.copy(alpha = 0.16f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = serviceIconFor(service.iconType),
                            contentDescription = null,
                            tint = accent,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = service.title.resolve(language),
                            style = MaterialTheme.typography.titleMedium,
                            color = PureSilk,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = service.subtitle.resolve(language),
                            style = MaterialTheme.typography.labelMedium,
                            color = AmberGlow,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Surface(
                    shape = RoundedCornerShape(50),
                    color = if (isInBrief) WhatsAppEmerald else VelvetElevated,
                    border = BorderStroke(
                        1.dp,
                        if (isInBrief) WhatsAppEmerald else CinemaGold.copy(alpha = 0.45f)
                    ),
                    modifier = Modifier
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(50))
                        .clickable(onClick = onToggleBrief)
                        .testTag("toggle_brief_${service.id}")
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = if (isInBrief) Icons.Filled.Check else Icons.Filled.Add,
                            contentDescription = null,
                            tint = if (isInBrief) ObsidianBg else CinemaGold,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isInBrief) {
                                if (language == AppLanguage.GUJARATI) "ઉમેર્યું" else "Added"
                            } else {
                                if (language == AppLanguage.GUJARATI) "બ્રીફ" else "Brief"
                            },
                            style = MaterialTheme.typography.labelMedium,
                            color = if (isInBrief) ObsidianBg else PureSilk,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = service.description.resolve(language),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = accent.copy(alpha = 0.14f)
                ) {
                    Text(
                        text = "⏱ ${service.turnaround.resolve(language)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = accent,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
                service.idealFor.take(2).forEach { tag ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = VelvetElevated
                    ) {
                        Text(
                            text = tag.resolve(language),
                            style = MaterialTheme.typography.labelSmall,
                            color = PureSilk,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                }
            }
        }
    }
}
