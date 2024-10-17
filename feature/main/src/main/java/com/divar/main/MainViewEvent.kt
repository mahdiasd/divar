package com.divar.main

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class MainUiState(
    val isLoading: Boolean = true,
) : UiState


sealed class MainUiEvent : UiEvent {
}
