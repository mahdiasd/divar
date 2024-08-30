package com.divar.home

import androidx.compose.runtime.Stable
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
) : UiState


sealed class HomeUiEvent : UiEvent {
}
