package com.divar.main

import androidx.lifecycle.SavedStateHandle
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
) : BaseViewModel<MainUiState, MainUiEvent>() {

    override fun createInitialState() = MainUiState()

    override fun onTriggerEvent(event: MainUiEvent) {
    }

}
