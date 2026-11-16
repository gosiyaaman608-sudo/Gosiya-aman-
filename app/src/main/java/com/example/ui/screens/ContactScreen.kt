package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.StudioCatalog
import com.example.model.AppLanguage
import com.example.model.WhatsAppDesk
import com.example.ui.StudioUiState
import com.example.ui.components.WhatsAppButton
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
import com.example.util.IntentHelpers

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactScreen(
    state: StudioUiState,
    onToggleService: (String) -> Unit,
    onClientNameChange: (String) -> Unit,
    onBusinessNameChange: (String) -> Unit,
    onNotesChange: (String) -> Unit,
    onSelectWhatsappDesk: (String) -> Unit,
    buildMessage: () -> String,
    modifier: Modifier = Modifier
) {
    val language = state.language
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("contact_screen"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "ફ્રી નાનો ડેમો અને સંપર્ક 📲"
                    } else {
                        "Book a Mini Demo & Contact 📲"
                    },
                    style = MaterialTheme.typography.headlineSmall,
                    color = PureSilk
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (language == AppLanguage.GUJARATI) {
                        "જો તમને અમારી સર્વિસમાં રસ હોય, તો અમે તમને એક નાનો ડેમો પણ બતાવી શકીએ છીએ."
                    } else {
                        "Interested in our creative AI ads? Message us directly on WhatsApp or Instagram for a mini sample demo."
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                StudioCatalog.whatsappDesks.forEach { desk ->
                    WhatsAppDeskCard(
                        desk = desk,
                        language = language,
                        onChatNow = {
                            IntentHelpers.openWhatsApp(context, desk.number, buildMessage())
                        },
                        onDial = {
                            IntentHelpers.dialNumber(context, desk.number)
                        },
                        onCopy = {
                            IntentHelpers.copyToClipboard(context, "Flickora WhatsApp", desk.number)
                        }
                    )
                }
            }
        }

        item {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = VelvetSurface,
                border = BorderStroke(1.dp, RoseCoral.copy(alpha = 0.45f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .clickable {
                        IntentHelpers.openInstagram(context, StudioCatalog.INSTAGRAM_URL)
                    }
                    .testTag("instagram_contact_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(
                                    Brush.linearGradient(
                                        listOf(RoseCoral, ElectricViolet)
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CameraAlt,
                                contentDescription = stringResource(R.string.cd_instagram_icon),
                                tint = PureSilk
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Instagram • ${StudioCatalog.INSTAGRAM_HANDLE}",
                                style = MaterialTheme.typography.titleMedium,
                                color = PureSilk
                            )
                            Text(
                                text = if (language == AppLanguage.GUJARATI) {
                                    "અમારા લેટેસ્ટ રીલ્સ અને પોસ્ટર્સ જુઓ"
                                } else {
                                    "Watch latest AI reels, posters & showcases"
                                },
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Surface(
                        shape = RoundedCornerShape(50),
                        color = RoseCoral.copy(alpha = 0.16f),
                        border = BorderStroke(1.dp, RoseCoral.copy(alpha = 0.6f))
                    ) {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "ફોલો કરો" else "Open",
                            style = MaterialTheme.typography.labelLarge,
                            color = RoseCoral,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        item {
            Surface(
                shape = RoundedCornerShape(22.dp),
                color = VelvetSurface,
                border = BorderStroke(1.dp, CinemaGold.copy(alpha = 0.4f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) {
                            "તમારી કસ્ટમ ડેમો રિક્વેસ્ટ મોકલો"
                        } else {
                            "Build Your Custom Demo Request"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = CinemaGold
                    )

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
                        value = state.inquiryClientName,
                        onValueChange = onClientNameChange,
                        label = {
                            Text(if (language == AppLanguage.GUJARATI) "તમારું નામ" else "Your Name")
                        },
                        singleLine = true,
                        colors = fieldColors,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_client_name_input")
                    )

                    OutlinedTextField(
                        value = state.inquiryBusinessName,
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
                        singleLine = true,
                        colors = fieldColors,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_business_name_input")
                    )

                    Column {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) {
                                "પસંદ કરેલ સર્વિસ (ટેપ કરીને ઉમેરો/દૂર કરો)"
                            } else {
                                "Select Interested Services"
                            },
                            style = MaterialTheme.typography.titleSmall,
                            color = AmberGlow
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            StudioCatalog.services.forEach { service ->
                                val selected = state.selectedBriefServiceIds.contains(service.id)
                                FilterChip(
                                    selected = selected,
                                    onClick = { onToggleService(service.id) },
                                    label = { Text(service.title.resolve(language)) },
                                    leadingIcon = if (selected) {
                                        {
                                            Icon(
                                                imageVector = Icons.Filled.CheckCircle,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    } else null,
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = CinemaGold,
                                        selectedLabelColor = ObsidianBg,
                                        selectedLeadingIconColor = ObsidianBg,
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

                    OutlinedTextField(
                        value = state.inquiryNotes,
                        onValueChange = onNotesChange,
                        label = {
                            Text(
                                if (language == AppLanguage.GUJARATI) {
                                    "વધારાની વિગત / બજેટ / ભાષા"
                                } else {
                                    "Additional Details / Language / Goal"
                                }
                            )
                        },
                        minLines = 3,
                        colors = fieldColors,
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("inquiry_notes_input")
                    )

                    Column {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) {
                                "WhatsApp નંબર પસંદ કરો"
                            } else {
                                "Choose WhatsApp Desk"
                            },
                            style = MaterialTheme.typography.titleSmall,
                            color = AmberGlow
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            StudioCatalog.whatsappDesks.forEach { desk ->
                                val selected = state.selectedWhatsappNumber == desk.number
                                FilterChip(
                                    selected = selected,
                                    onClick = { onSelectWhatsappDesk(desk.number) },
                                    label = { Text(desk.number) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = WhatsAppEmerald,
                                        selectedLabelColor = ObsidianBg,
                                        containerColor = VelvetElevated,
                                        labelColor = PureSilk
                                    ),
                                    border = FilterChipDefaults.filterChipBorder(
                                        enabled = true,
                                        selected = selected,
                                        borderColor = VelvetStroke,
                                        selectedBorderColor = WhatsAppEmerald
                                    )
                                )
                            }
                        }
                    }

                    WhatsAppButton(
                        label = if (language == AppLanguage.GUJARATI) {
                            "WhatsApp પર ડેમો રિક્વેસ્ટ મોકલો (${state.selectedWhatsappNumber})"
                        } else {
                            "Send Demo Request (${state.selectedWhatsappNumber})"
                        },
                        onClick = {
                            IntentHelpers.openWhatsApp(
                                context,
                                state.selectedWhatsappNumber,
                                buildMessage()
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "contact_send_whatsapp_button"
                    )

                    OutlinedButton(
                        onClick = {
                            IntentHelpers.shareText(
                                context,
                                StudioCatalog.STUDIO_NAME,
                                buildMessage()
                            )
                        },
                        shape = RoundedCornerShape(14.dp),
                        border = BorderStroke(1.dp, VelvetStroke),
                        modifier = Modifier
                            .fillMaxWidth()
                            .minimumInteractiveComponentSize()
                            .testTag("contact_share_brief_button")
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = null,
                            tint = PureSilk,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (language == AppLanguage.GUJARATI) {
                                "અન્ય એપમાં બ્રીફ શેર કરો"
                            } else {
                                "Share Brief via Other Apps"
                            },
                            color = PureSilk
                        )
                    }
                }
            }
        }

        item {
            Surface(
                shape = RoundedCornerShape(18.dp),
                color = VelvetElevated,
                border = BorderStroke(1.dp, VelvetStroke),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎬 ${StudioCatalog.STUDIO_NAME}",
                        style = MaterialTheme.typography.titleMedium,
                        color = PureSilk
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = StudioCatalog.STUDIO_TAGLINE,
                        style = MaterialTheme.typography.labelLarge,
                        color = CinemaGold
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun WhatsAppDeskCard(
    desk: WhatsAppDesk,
    language: AppLanguage,
    onChatNow: () -> Unit,
    onDial: () -> Unit,
    onCopy: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        color = WhatsAppContainer,
        border = BorderStroke(1.dp, WhatsAppEmerald.copy(alpha = 0.5f)),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable(onClick = onChatNow)
            .testTag("whatsapp_desk_${desk.number}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(WhatsAppEmerald),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = stringResource(R.string.cd_whatsapp_icon),
                        tint = ObsidianBg,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = desk.formattedNumber,
                        style = MaterialTheme.typography.titleMedium,
                        color = PureSilk,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = desk.role.resolve(language),
                        style = MaterialTheme.typography.labelMedium,
                        color = WhatsAppEmerald
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = onDial,
                    modifier = Modifier.testTag("dial_${desk.number}")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Call,
                        contentDescription = "Call ${desk.number}",
                        tint = PureSilk
                    )
                }
                IconButton(
                    onClick = onCopy,
                    modifier = Modifier.testTag("copy_${desk.number}")
                ) {
                    Icon(
                        imageVector = Icons.Filled.ContentCopy,
                        contentDescription = "Copy ${desk.number}",
                        tint = AmberGlow
                    )
                }
            }
        }
    }
}
