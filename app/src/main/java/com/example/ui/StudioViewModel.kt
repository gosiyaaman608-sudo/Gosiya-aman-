package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AiConceptRepository
import com.example.data.FirebaseAiConceptRepository
import com.example.data.StudioCatalog
import com.example.model.AdConceptDemo
import com.example.model.AdConceptRequest
import com.example.model.AppLanguage
import com.example.model.BusinessCategory
import com.example.model.ScriptLanguage
import com.example.model.ServiceCategory
import com.example.model.ShowcaseItem
import com.example.model.StudioService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class StudioTab {
    HOME,
    SERVICES,
    DEMO_LAB,
    CONTACT
}

sealed interface ConceptGenerationState {
    data object Idle : ConceptGenerationState
    data object Loading : ConceptGenerationState
    data class Success(val demo: AdConceptDemo) : ConceptGenerationState
    data class Error(val message: String) : ConceptGenerationState
}

data class StudioUiState(
    val language: AppLanguage = AppLanguage.GUJARATI,
    val activeTab: StudioTab = StudioTab.HOME,
    val serviceFilter: ServiceCategory = ServiceCategory.ALL,
    val selectedBriefServiceIds: Set<String> = emptySet(),
    val activeShowcase: ShowcaseItem? = null,
    val activeServiceDetail: StudioService? = null,
    val demoBusinessName: String = "",
    val demoOfferHighlight: String = "",
    val demoCategory: BusinessCategory = BusinessCategory.FASHION,
    val demoServiceId: String = StudioCatalog.services.first().id,
    val demoScriptLanguage: ScriptLanguage = ScriptLanguage.GUJARATI,
    val conceptState: ConceptGenerationState = ConceptGenerationState.Idle,
    val inquiryClientName: String = "",
    val inquiryBusinessName: String = "",
    val inquiryNotes: String = "",
    val selectedWhatsappNumber: String = StudioCatalog.whatsappDesks.first().number
)

class StudioViewModel(
    private val aiConceptRepository: AiConceptRepository = FirebaseAiConceptRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudioUiState())
    val uiState: StateFlow<StudioUiState> = _uiState.asStateFlow()

    fun toggleLanguage() {
        _uiState.update { current ->
            val next = if (current.language == AppLanguage.GUJARATI) {
                AppLanguage.ENGLISH
            } else {
                AppLanguage.GUJARATI
            }
            current.copy(language = next)
        }
    }

    fun selectTab(tab: StudioTab) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun selectServiceFilter(filter: ServiceCategory) {
        _uiState.update { it.copy(serviceFilter = filter) }
    }

    fun toggleServiceInBrief(serviceId: String) {
        _uiState.update { current ->
            val updated = current.selectedBriefServiceIds.toMutableSet()
            if (updated.contains(serviceId)) {
                updated.remove(serviceId)
            } else {
                updated.add(serviceId)
            }
            current.copy(selectedBriefServiceIds = updated)
        }
    }

    fun openShowcase(item: ShowcaseItem) {
        _uiState.update { it.copy(activeShowcase = item) }
    }

    fun closeShowcase() {
        _uiState.update { it.copy(activeShowcase = null) }
    }

    fun openServiceDetail(service: StudioService) {
        _uiState.update { it.copy(activeServiceDetail = service) }
    }

    fun closeServiceDetail() {
        _uiState.update { it.copy(activeServiceDetail = null) }
    }

    fun requestDemoForService(serviceId: String) {
        _uiState.update { current ->
            current.copy(
                activeServiceDetail = null,
                activeShowcase = null,
                selectedBriefServiceIds = current.selectedBriefServiceIds + serviceId,
                demoServiceId = serviceId,
                activeTab = StudioTab.CONTACT
            )
        }
    }

    fun tryDemoLabForService(serviceId: String) {
        _uiState.update { current ->
            current.copy(
                activeServiceDetail = null,
                activeShowcase = null,
                demoServiceId = serviceId,
                activeTab = StudioTab.DEMO_LAB
            )
        }
    }

    fun updateDemoBusinessName(value: String) {
        _uiState.update { it.copy(demoBusinessName = value) }
    }

    fun updateDemoOfferHighlight(value: String) {
        _uiState.update { it.copy(demoOfferHighlight = value) }
    }

    fun updateDemoCategory(category: BusinessCategory) {
        _uiState.update { it.copy(demoCategory = category) }
    }

    fun updateDemoService(serviceId: String) {
        _uiState.update { it.copy(demoServiceId = serviceId) }
    }

    fun updateDemoScriptLanguage(scriptLanguage: ScriptLanguage) {
        _uiState.update { it.copy(demoScriptLanguage = scriptLanguage) }
    }

    fun generateDemoConcept() {
        val current = _uiState.value
        val request = AdConceptRequest(
            businessName = current.demoBusinessName.ifBlank {
                if (current.language == AppLanguage.GUJARATI) "આરાધ્યા ફેશન" else "Aaradhya Fashion"
            },
            category = current.demoCategory,
            serviceId = current.demoServiceId,
            scriptLanguage = current.demoScriptLanguage,
            offerHighlight = current.demoOfferHighlight
        )
        viewModelScope.launch {
            _uiState.update { it.copy(conceptState = ConceptGenerationState.Loading) }
            try {
                val result = aiConceptRepository.generateAdConcept(request)
                result.fold(
                    onSuccess = { demo ->
                        _uiState.update { it.copy(conceptState = ConceptGenerationState.Success(demo)) }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(
                                conceptState = ConceptGenerationState.Error(
                                    error.localizedMessage ?: "Unable to prepare demo concept."
                                )
                            )
                        }
                    }
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        conceptState = ConceptGenerationState.Error(
                            e.localizedMessage ?: "Unexpected error preparing demo."
                        )
                    )
                }
            }
        }
    }

    fun sendConceptToBooking(demo: AdConceptDemo) {
        val serviceId = _uiState.value.demoServiceId
        val notes = buildString {
            appendLine("Demo Concept: ${demo.hookHeadline}")
            appendLine("Voiceover: ${demo.voiceoverScript}")
        }.trim()
        _uiState.update { current ->
            current.copy(
                inquiryBusinessName = demo.businessName,
                inquiryNotes = notes,
                selectedBriefServiceIds = current.selectedBriefServiceIds + serviceId,
                activeTab = StudioTab.CONTACT
            )
        }
    }

    fun updateInquiryClientName(value: String) {
        _uiState.update { it.copy(inquiryClientName = value) }
    }

    fun updateInquiryBusinessName(value: String) {
        _uiState.update { it.copy(inquiryBusinessName = value) }
    }

    fun updateInquiryNotes(value: String) {
        _uiState.update { it.copy(inquiryNotes = value) }
    }

    fun selectWhatsappDesk(number: String) {
        _uiState.update { it.copy(selectedWhatsappNumber = number) }
    }

    fun buildWhatsAppInquiryMessage(): String {
        val state = _uiState.value
        val selectedServices = StudioCatalog.services
            .filter { state.selectedBriefServiceIds.contains(it.id) }
            .map { it.title.resolve(state.language) }

        return buildString {
            if (state.language == AppLanguage.GUJARATI) {
                appendLine("નમસ્તે Flickora AI Studio! 🎬")
                appendLine("મને તમારા AI વિડિયો / પોસ્ટર ડેમોમાં રસ છે.")
                if (state.inquiryClientName.isNotBlank()) {
                    appendLine("નામ: ${state.inquiryClientName.trim()}")
                }
                if (state.inquiryBusinessName.isNotBlank()) {
                    appendLine("બિઝનેસ/શોપ: ${state.inquiryBusinessName.trim()}")
                }
                if (selectedServices.isNotEmpty()) {
                    appendLine("પસંદ કરેલ સર્વિસ:")
                    selectedServices.forEach { appendLine("✨ $it") }
                }
                if (state.inquiryNotes.isNotBlank()) {
                    appendLine("વિગત: ${state.inquiryNotes.trim()}")
                }
                append("કૃપા કરીને મને એક નાનો ડેમો અને પ્રાઇસ વિગત મોકલો.")
            } else {
                appendLine("Hello Flickora AI Studio! 🎬")
                appendLine("I am interested in a mini demo for my business/product.")
                if (state.inquiryClientName.isNotBlank()) {
                    appendLine("Name: ${state.inquiryClientName.trim()}")
                }
                if (state.inquiryBusinessName.isNotBlank()) {
                    appendLine("Business/Shop: ${state.inquiryBusinessName.trim()}")
                }
                if (selectedServices.isNotEmpty()) {
                    appendLine("Interested Services:")
                    selectedServices.forEach { appendLine("✨ $it") }
                }
                if (state.inquiryNotes.isNotBlank()) {
                    appendLine("Notes: ${state.inquiryNotes.trim()}")
                }
                append("Please share a quick sample demo and details.")
            }
        }
    }
}
