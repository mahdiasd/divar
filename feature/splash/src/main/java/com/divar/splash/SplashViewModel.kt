package com.divar.splash

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.location.GetUserCityUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import com.divar.utils.dLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getUserCityUseCase: GetUserCityUseCase
) : BaseViewModel<SplashUiState, SplashUiEvent>() {

    init {
        getUserCity()
    }

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    setState { copy(userIsSelectedCity = true) }
                }.onFailure { apiError ->
                    setState { copy(userIsSelectedCity = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
                currentState.userIsSelectedCity.dLog("userIsSelectedCity: ")
            }
        }
    }

    override fun createInitialState() = SplashUiState()

    override fun onTriggerEvent(event: SplashUiEvent) {
    }

}
