package com.divar.ads_detail

import android.content.Context
import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.Ads
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class AdsDetailUiState(
    val isLoading: Boolean = true,
    val ads: Ads? = null,
    val showFullScreenSlider: Boolean = false
) : UiState


sealed class AdsDetailUiEvent : UiEvent {
    data object OnRefresh : AdsDetailUiEvent()
    data class ShowFullScreenSlider(val isFullScreen: Boolean) : AdsDetailUiEvent()
    data class OnShareClick(val context: Context) : AdsDetailUiEvent()
}

typealias OnAction = (AdsDetailUiEvent) -> Unit
