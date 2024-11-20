package com.divar.auth

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class AuthUiState(
    val isLoading: Boolean = true,
    val screenMode: ScreenMode = ScreenMode.Login,
) : UiState {
    class OnChangeMode(screenMode: ScreenMode) : AuthUiEvent()
}

sealed class AuthUiEvent : UiEvent {
}

typealias OnAction = (AuthUiEvent) -> Unit

enum class ScreenMode { Login, Register }
