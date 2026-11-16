package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.MovieCreation
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.StudioCatalog
import com.example.model.AppLanguage
import com.example.ui.components.FlickoraTopBar
import com.example.ui.components.ServiceDetailSheet
import com.example.ui.components.ShowcaseDetailSheet
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.DemoLabScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ServicesScreen
import com.example.ui.theme.CinemaGold
import com.example.ui.theme.ElectricViolet
import com.example.ui.theme.ObsidianBg
import com.example.ui.theme.PureSilk
import com.example.ui.theme.VelvetSurface
import com.example.util.IntentHelpers

private data class NavDestination(
    val tab: StudioTab,
    val guLabel: String,
    val enLabel: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

private val destinations = listOf(
    NavDestination(
        tab = StudioTab.HOME,
        guLabel = "સ્ટુડિયો",
        enLabel = "Studio",
        selectedIcon = Icons.Filled.MovieCreation,
        unselectedIcon = Icons.Outlined.MovieCreation,
        testTag = "nav_tab_home"
    ),
    NavDestination(
        tab = StudioTab.SERVICES,
        guLabel = "સર્વિસ",
        enLabel = "Services",
        selectedIcon = Icons.Filled.GridView,
        unselectedIcon = Icons.Outlined.GridView,
        testTag = "nav_tab_services"
    ),
    NavDestination(
        tab = StudioTab.DEMO_LAB,
        guLabel = "ડેમો લેબ",
        enLabel = "Demo Lab",
        selectedIcon = Icons.Filled.AutoAwesome,
        unselectedIcon = Icons.Outlined.AutoAwesome,
        testTag = "nav_tab_demo_lab"
    ),
    NavDestination(
        tab = StudioTab.CONTACT,
        guLabel = "સંપર્ક",
        enLabel = "Contact",
        selectedIcon = Icons.AutoMirrored.Filled.Chat,
        unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
        testTag = "nav_tab_contact"
    )
)

@Composable
fun FlickoraApp(
    viewModel: StudioViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (state.activeTab != StudioTab.HOME && state.activeShowcase == null && state.activeServiceDetail == null) {
        BackHandler {
            viewModel.selectTab(StudioTab.HOME)
        }
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        ObsidianBg,
                        ElectricViolet.copy(alpha = 0.06f),
                        ObsidianBg
                    )
                )
            )
    ) {
        val useRail = maxWidth >= 600.dp

        if (useRail) {
            Row(modifier = Modifier.fillMaxSize()) {
                NavigationRail(
                    containerColor = VelvetSurface,
                    contentColor = PureSilk,
                    modifier = Modifier
                        .fillMaxHeight()
                        .statusBarsPadding()
                ) {
                    destinations.forEach { dest ->
                        val selected = state.activeTab == dest.tab
                        val label = if (state.language == AppLanguage.GUJARATI) dest.guLabel else dest.enLabel
                        NavigationRailItem(
                            selected = selected,
                            onClick = { viewModel.selectTab(dest.tab) },
                            icon = {
                                NavItemIcon(
                                    dest = dest,
                                    selected = selected,
                                    briefCount = state.selectedBriefServiceIds.size
                                )
                            },
                            label = { Text(label, style = MaterialTheme.typography.labelSmall) },
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = ObsidianBg,
                                selectedTextColor = CinemaGold,
                                indicatorColor = CinemaGold,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.testTag(dest.testTag)
                        )
                    }
                }

                Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                    FlickoraTopBar(
                        language = state.language,
                        onToggleLanguage = viewModel::toggleLanguage,
                        modifier = Modifier.statusBarsPadding()
                    )
                    Box(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        ScreenSwitcher(
                            state = state,
                            viewModel = viewModel,
                            modifier = Modifier.widthIn(max = 860.dp)
                        )
                    }
                }
            }
        } else {
            Scaffold(
                containerColor = ObsidianBg,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                topBar = {
                    FlickoraTopBar(
                        language = state.language,
                        onToggleLanguage = viewModel::toggleLanguage,
                        modifier = Modifier.statusBarsPadding()
                    )
                },
                bottomBar = {
                    NavigationBar(
                        containerColor = VelvetSurface,
                        contentColor = PureSilk,
                        windowInsets = WindowInsets.navigationBars
                    ) {
                        destinations.forEach { dest ->
                            val selected = state.activeTab == dest.tab
                            val label = if (state.language == AppLanguage.GUJARATI) dest.guLabel else dest.enLabel
                            NavigationBarItem(
                                selected = selected,
                                onClick = { viewModel.selectTab(dest.tab) },
                                icon = {
                                    NavItemIcon(
                                        dest = dest,
                                        selected = selected,
                                        briefCount = state.selectedBriefServiceIds.size
                                    )
                                },
                                label = {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelSmall,
                                        maxLines = 1
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = ObsidianBg,
                                    selectedTextColor = CinemaGold,
                                    indicatorColor = CinemaGold,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.testTag(dest.testTag)
                            )
                        }
                    }
                }
            ) { innerPadding ->
                ScreenSwitcher(
                    state = state,
                    viewModel = viewModel,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
    }

    state.activeShowcase?.let { showcase ->
        ShowcaseDetailSheet(
            showcase = showcase,
            language = state.language,
            onDismiss = viewModel::closeShowcase,
            onRequestSimilarOnWhatsApp = { item ->
                val msg = if (state.language == AppLanguage.GUJARATI) {
                    "નમસ્તે Flickora AI Studio! મને '${item.title.gu}' જેવો એક નાનો ડેમો બનાવવો છે. વિગત મોકલો."
                } else {
                    "Hello Flickora AI Studio! I would like a mini demo similar to '${item.title.en}'. Please share details."
                }
                IntentHelpers.openWhatsApp(context, StudioCatalog.whatsappDesks.first().number, msg)
            },
            onOpenInDemoLab = viewModel::tryDemoLabForService
        )
    }

    state.activeServiceDetail?.let { service ->
        ServiceDetailSheet(
            service = service,
            language = state.language,
            isInBrief = state.selectedBriefServiceIds.contains(service.id),
            onDismiss = viewModel::closeServiceDetail,
            onToggleBrief = viewModel::toggleServiceInBrief,
            onBookDemo = viewModel::requestDemoForService
        )
    }
}

@Composable
private fun NavItemIcon(
    dest: NavDestination,
    selected: Boolean,
    briefCount: Int
) {
    val iconVector = if (selected) dest.selectedIcon else dest.unselectedIcon
    if (dest.tab == StudioTab.CONTACT && briefCount > 0) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = ElectricViolet,
                    contentColor = PureSilk
                ) {
                    Text("$briefCount")
                }
            }
        ) {
            Icon(imageVector = iconVector, contentDescription = dest.enLabel)
        }
    } else {
        Icon(imageVector = iconVector, contentDescription = dest.enLabel)
    }
}

@Composable
private fun ScreenSwitcher(
    state: StudioUiState,
    viewModel: StudioViewModel,
    modifier: Modifier = Modifier
) {
    Crossfade(
        targetState = state.activeTab,
        label = "studio_tab_crossfade",
        modifier = modifier
    ) { tab ->
        when (tab) {
            StudioTab.HOME -> HomeScreen(
                language = state.language,
                onOpenShowcase = viewModel::openShowcase,
                onOpenService = viewModel::openServiceDetail,
                onNavigateToServices = { viewModel.selectTab(StudioTab.SERVICES) },
                onNavigateToDemoLab = { viewModel.selectTab(StudioTab.DEMO_LAB) },
                onNavigateToContact = { viewModel.selectTab(StudioTab.CONTACT) }
            )

            StudioTab.SERVICES -> ServicesScreen(
                language = state.language,
                activeFilter = state.serviceFilter,
                selectedBriefIds = state.selectedBriefServiceIds,
                onSelectFilter = viewModel::selectServiceFilter,
                onToggleBrief = viewModel::toggleServiceInBrief,
                onOpenServiceDetail = viewModel::openServiceDetail,
                onProceedToBriefBooking = { viewModel.selectTab(StudioTab.CONTACT) }
            )

            StudioTab.DEMO_LAB -> DemoLabScreen(
                state = state,
                onBusinessNameChange = viewModel::updateDemoBusinessName,
                onOfferChange = viewModel::updateDemoOfferHighlight,
                onCategorySelect = viewModel::updateDemoCategory,
                onServiceSelect = viewModel::updateDemoService,
                onScriptLanguageSelect = viewModel::updateDemoScriptLanguage,
                onGenerateConcept = viewModel::generateDemoConcept,
                onSendToBooking = viewModel::sendConceptToBooking
            )

            StudioTab.CONTACT -> ContactScreen(
                state = state,
                onToggleService = viewModel::toggleServiceInBrief,
                onClientNameChange = viewModel::updateInquiryClientName,
                onBusinessNameChange = viewModel::updateInquiryBusinessName,
                onNotesChange = viewModel::updateInquiryNotes,
                onSelectWhatsappDesk = viewModel::selectWhatsappDesk,
                buildMessage = viewModel::buildWhatsAppInquiryMessage
            )
        }
    }
}
