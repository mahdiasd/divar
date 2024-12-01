package com.divar.location

import androidx.compose.runtime.Stable
import com.divar.domain.model.location.City
import com.divar.domain.model.location.LocationScreenType
import com.divar.domain.model.location.Neighborhood
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class LocationUiState(
    val isLoading: Boolean = true,
    val searchText: String = "",
    val cities: ImmutableList<City>? = null,
    val selectedCity: City? = null,
    val cityIsSelected: Boolean = false,
    val locationScreenType: LocationScreenType = LocationScreenType.FromLogin,

    val selectedNeighborhood: Neighborhood? = null,
    val onBack: Boolean = false,
) : UiState


sealed class LocationUiEvent : UiEvent {
    data object OnRefresh : LocationUiEvent()
    data class OnSearch(val text: String) : LocationUiEvent()
    data class OnCity(val city: City) : LocationUiEvent()
    data class OnNeighborhood(val neighborhood: Neighborhood) : LocationUiEvent()
}

typealias OnAction = (LocationUiEvent) -> Unit