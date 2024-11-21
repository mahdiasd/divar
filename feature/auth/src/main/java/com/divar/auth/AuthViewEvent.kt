package com.divar.auth

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class AuthUiState(
    val isLoading: Boolean = true,
    val screenMode: ScreenMode = ScreenMode.Login,
    val mobile: String = "",
    val password: String = "",
    val repeatPassword: String = "",
) : UiState

sealed class AuthUiEvent : UiEvent {
    data class OnTextChanged(val typingType: TypingType) : AuthUiEvent()
    data class OnChangeMode(val screenMode: ScreenMode) : AuthUiEvent()
}

typealias OnAction = (AuthUiEvent) -> Unit

enum class ScreenMode { Login, Register }

sealed class TypingType {
    data class Mobile(val text: String) : TypingType()
    data class Password(val text: String) : TypingType()
    data class RepeatPassword(val text: String) : TypingType()
}