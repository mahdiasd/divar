package com.divar.ui.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State : UiState, Event : UiEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }

    @Stable
    val currentState: State get() = uiState.value

    @Stable
    abstract fun createInitialState(): State

    @Stable
    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)

    @Stable
    val uiState: StateFlow<State> = _uiState

    @Stable
    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()

    @Stable
    val uiEvent: SharedFlow<Event> = _uiEvent

    fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }
}