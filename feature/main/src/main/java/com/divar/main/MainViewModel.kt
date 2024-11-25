package com.divar.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.usecase.user.IsLoginUseCase
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val isLoginUseCase: IsLoginUseCase
) : BaseViewModel<MainUiState, MainUiEvent>() {

    init {
        checkUserLoggedIn()
    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {
            isLoginUseCase().collect {
                setState { copy(isUserLoggedIn = it) }
            }
        }
    }

    override fun createInitialState() = MainUiState()

    override fun onTriggerEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.OnChangeTab -> {
                setState { copy(selectedIndex = event.index) }
            }
        }
    }


}
