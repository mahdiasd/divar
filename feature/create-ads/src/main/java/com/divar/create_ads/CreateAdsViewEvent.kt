package com.divar.create_ads

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class CreateAdsUiState(
    val isLoading: Boolean = true,
    val screenStep: ScreenStep = ScreenStep.Step1
) : UiState

enum class ScreenStep { Step1, Step2 }
sealed class CreateAdsUiEvent : UiEvent {
}

typealias OnAction = (CreateAdsUiEvent) -> Unit
