package com.divar.ads_detail

import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.Ads
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class AdsDetailUiState(
    val isLoading: Boolean = true,
    val ads: Ads? = null
) : UiState


sealed class AdsDetailUiEvent : UiEvent {
    data object OnRefresh : AdsDetailUiEvent()
}

typealias OnAction = (AdsDetailUiEvent) -> Unit
