package com.divar.auth

import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.user.LoginUseCase
import com.divar.domain.usecase.user.RegisterUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
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
                if (!validate()) return
                when (currentState.screenMode) {
                    ScreenMode.Login -> login()
                    ScreenMode.Register -> register()
                }

            }
        }
    }

    private fun validate(): Boolean {
        return when {
            currentState.mobile.isEmpty() -> {
                setUiMessage(UiMessage(intValue = com.divar.ui.R.string.enter_mobile))
                false
            }

            currentState.password.isEmpty() -> {
                setUiMessage(UiMessage(intValue = com.divar.ui.R.string.enter_password))
                false
            }

            currentState.screenMode == ScreenMode.Register && currentState.repeatPassword.isEmpty() -> {
                setUiMessage(UiMessage(intValue = com.divar.ui.R.string.enter_repeat_password))
                false
            }

            currentState.screenMode == ScreenMode.Register && currentState.password != currentState.repeatPassword -> {
                setUiMessage(UiMessage(intValue = com.divar.ui.R.string.password_repeat_not_same))
                false
            }

            else -> true
        }
    }

    private fun login() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            loginUseCase.invoke(currentState.mobile, currentState.password).collect {
                it.onSuccess {
                    setState { copy(isLoading = false) }
                    setState { copy(user = it) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun register() {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            registerUseCase.invoke(currentState.mobile, currentState.password, currentState.repeatPassword).collect {
                it.onSuccess {
                    setState { copy(user = it , isLoading = false) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }
}

