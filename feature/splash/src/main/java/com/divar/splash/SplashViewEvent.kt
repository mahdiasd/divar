package com.divar.splash

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class SplashUiState(
    val userIsSelectedCity: Boolean? = null,
) : UiState


sealed class SplashUiEvent : UiEvent {
}
