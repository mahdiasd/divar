package com.divar.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.location.City
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.location.GetCitiesUseCase
import com.divar.domain.usecase.location.SaveCityUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCitiesUseCase: GetCitiesUseCase,
    private val saveCityUseCase: SaveCityUseCase,
) : BaseViewModel<LocationUiState, LocationUiEvent>() {

    private var originalCities: MutableList<City> = mutableListOf()

    init {
        getCities()
    }

    private fun getCities() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            getCitiesUseCase.invoke().collect {
                it.onSuccess { cities ->
                    originalCities = cities.toMutableList()
                    setState {
                        copy(
                            isLoading = false,
                            cities = cities.toImmutableList()
                        )
                    }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun saveCity() {
        viewModelScope.launch {
            saveCityUseCase.invoke(currentState.selectedCity!!)
        }
        setState { copy(cityIsSelected = true) }
    }

    override fun createInitialState() = LocationUiState()

    override fun onTriggerEvent(event: LocationUiEvent) {
        when (event) {
            LocationUiEvent.OnRefresh -> getCities()
            is LocationUiEvent.OnSearch -> {
                setState {
                    copy(
                        searchText = event.text,
                        cities = originalCities.filter { it.name.contains(event.text) }.toImmutableList()
                    )
                }
            }

            is LocationUiEvent.OnCity -> {
                setState { copy(selectedCity = event.city) }
                saveCity()
            }
        }
    }

}
