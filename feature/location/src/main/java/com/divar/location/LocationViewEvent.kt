package com.divar.location

import androidx.compose.runtime.Stable
import com.divar.domain.model.location.City
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class LocationUiState(
    val isLoading: Boolean = true,
    val searchText: String = "",
    val cities: ImmutableList<City>? = null,
    val selectedCity: City? = null,
    val cityIsSelected: Boolean = false
) : UiState


sealed class LocationUiEvent : UiEvent {
    data object OnRefresh : LocationUiEvent()
    data class OnSearch(val text: String) : LocationUiEvent()
    data class OnCity(val city: City) : LocationUiEvent()
}

typealias OnAction = (LocationUiEvent) -> Unit