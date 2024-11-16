package com.divar.ads_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.ads.GetAdsDetailUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdsDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsDetailUseCase: GetAdsDetailUseCase
) : BaseViewModel<AdsDetailUiState, AdsDetailUiEvent>() {
    private var adsId: Long? = null


    init {
        getInitData()
    }

    private fun getInitData() {
        savedStateHandle?.get<Int>("id")?.let {
            adsId = it.toLong()
            getAds()
        }
    }

    private fun getAds() {
        if (adsId != null) {
            viewModelScope.launch {
                getAdsDetailUseCase.invoke(adsId!!).collect {
                    it.onSuccess {
                        setState { copy(isLoading = false, ads = it) }
                    }.onFailure { apiError ->
                        setState { copy(isLoading = false) }
                        setUiMessage(UiMessage(stringValue = apiError.message))
                    }
                }
            }
        }
    }

    override fun createInitialState() = AdsDetailUiState()

    override fun onTriggerEvent(event: AdsDetailUiEvent) {
        when (event) {
            AdsDetailUiEvent.OnRefresh -> getAds()
            is AdsDetailUiEvent.ShowFullScreenSlider -> {
                setState { copy(showFullScreenSlider = event.isFullScreen) }
            }
        }
    }

}
