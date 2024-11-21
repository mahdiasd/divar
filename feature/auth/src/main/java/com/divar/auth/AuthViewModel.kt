package com.divar.auth

import androidx.lifecycle.SavedStateHandle
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
) : BaseViewModel<AuthUiState, AuthUiEvent>() {

    override fun createInitialState() = AuthUiState()

    override fun onTriggerEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.OnChangeMode -> {
                setState { copy(screenMode = event.screenMode) }
            }

            is AuthUiEvent.OnTextChanged -> {
                when (event.typingType) {
                    is TypingType.Mobile -> setState { copy(mobile = event.typingType.text) }
                    is TypingType.Password -> setState { copy(password = event.typingType.text) }
                    is TypingType.RepeatPassword -> setState { copy(repeatPassword = event.typingType.text) }
                }
            }

            AuthUiEvent.OnBtnClick -> {

            }
        }
    }

}
