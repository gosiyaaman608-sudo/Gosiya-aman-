package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.SampleAiConceptRepository
import com.example.data.StudioCatalog
import com.example.model.AdConceptRequest
import com.example.model.AppLanguage
import com.example.model.BusinessCategory
import com.example.model.ScriptLanguage
import com.example.ui.ConceptGenerationState
import com.example.ui.StudioTab
import com.example.ui.StudioViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `app name matches Flickora AI`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Flickora AI", appName)
    }

    @Test
    fun `catalog exposes all 7 studio services and both WhatsApp desks`() {
        assertEquals(7, StudioCatalog.services.size)
        assertEquals(listOf("6355675109", "9737526661"), StudioCatalog.whatsappDesks.map { it.number })
        assertEquals("@flickora_ai_studio", StudioCatalog.INSTAGRAM_HANDLE)
    }

    @Test
    fun `sample repository generates clearly labeled sample demo concept`() = runTest {
        val repo = SampleAiConceptRepository()
        val result = repo.generateAdConcept(
            AdConceptRequest(
                businessName = "Shagun Silk",
                category = BusinessCategory.FASHION,
                serviceId = "ai_model_ad",
                scriptLanguage = ScriptLanguage.GUJARATI,
                offerHighlight = "25% Festive Off"
            )
        )
        assertTrue(result.isSuccess)
        val demo = result.getOrThrow()
        assertTrue(demo.isSampleMode)
        assertTrue(demo.hookHeadline.contains("Shagun Silk"))
        assertTrue(demo.voiceoverScript.contains("25% Festive Off"))
        assertEquals(3, demo.scenes.size)
    }

    @Test
    fun `viewModel builds bilingual WhatsApp inquiry with selected services`() = runTest {
        val viewModel = StudioViewModel(SampleAiConceptRepository())
        viewModel.toggleServiceInBrief("ai_model_ad")
        viewModel.toggleServiceInBrief("premium_ai_posters")
        viewModel.updateInquiryClientName("Aman")
        viewModel.updateInquiryBusinessName("Flickora Demo Shop")

        val gujaratiMsg = viewModel.buildWhatsAppInquiryMessage()
        assertTrue(gujaratiMsg.contains("Aman"))
        assertTrue(gujaratiMsg.contains("Flickora Demo Shop"))
        assertTrue(gujaratiMsg.contains("AI મોડેલ એડવર્ટાઇઝમેન્ટ"))

        viewModel.toggleLanguage()
        assertEquals(AppLanguage.ENGLISH, viewModel.uiState.value.language)
        val englishMsg = viewModel.buildWhatsAppInquiryMessage()
        assertTrue(englishMsg.contains("AI Model Advertisement"))

        viewModel.updateDemoBusinessName("Royal Jewels")
        viewModel.generateDemoConcept()
        advanceUntilIdle()
        val conceptState = viewModel.uiState.value.conceptState
        assertTrue(conceptState is ConceptGenerationState.Success)
        viewModel.selectTab(StudioTab.CONTACT)
        assertEquals(StudioTab.CONTACT, viewModel.uiState.value.activeTab)
    }
}
