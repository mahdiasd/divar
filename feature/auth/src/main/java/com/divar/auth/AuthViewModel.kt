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
    }

}
