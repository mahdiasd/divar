package com.divar.filter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.model.parameter.DataType
import com.divar.domain.usecase.category.GetCategoriesUseCase
import com.divar.domain.usecase.parameter.GetParametersUseCase
import com.divar.ui.extension.toPrice
import com.divar.domain.model.filter.FilterClickType
import com.divar.domain.usecase.filter.ReadFilterFromCategoryUseCase
import com.divar.domain.usecase.filter.ReadFilterFromHomeUseCase
import com.divar.domain.usecase.filter.SaveFilterFromCategoryUseCase
import com.divar.domain.usecase.filter.SaveFilterFromHomeUseCase
import com.divar.ui.model.FromScreen
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import com.divar.utils.fromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getParameterUseCase: GetParametersUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,

    private val readFilterFromCategoryUseCase: ReadFilterFromCategoryUseCase,
    private val readFilterFromHomeUseCase: ReadFilterFromHomeUseCase,

    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase,
    private val saveFilterFromHomeUseCase: SaveFilterFromHomeUseCase
) : BaseViewModel<FilterUiState, FilterUiEvent>() {

    init {
        getInitData()
        getCategories()
    }

    private fun getInitData() {
        savedStateHandle?.get<String>("fromScreen")?.let { json ->
            setState { copy(fromScreen = json.fromJson<FromScreen>()!!) }
            getAdsFilter()
        }
    }

    private fun getAdsFilter() {
        viewModelScope.launch {
            when (currentState.fromScreen) {
                FromScreen.Home -> {
                    readFilterFromHomeUseCase.invoke().collect {
                        setState { copy(adsFilter = it ?: AdsFilter(searchText = "")) }
                    }
                }

                FromScreen.Category -> {
                    readFilterFromCategoryUseCase.invoke().collect {
                        setState { copy(adsFilter = it ?: AdsFilter(searchText = "")) }
                    }
                }
            }
        }
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        currentState.copy(allCategories = it.toImmutableList())
                    }
                }.onFailure { apiError ->
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    private fun getParameters() {
        currentState.adsFilter?.category?.id?.let { categoryId ->
            viewModelScope.launch {
                getParameterUseCase.invoke(categoryId).collect {
                    it.onSuccess {
                        setState { copy(adsFilter = currentState.adsFilter?.copy(parameters = it.toImmutableList())) }
                    }.onFailure { apiError ->
                        setState { copy(isLoading = false) }
                        setUiMessage(UiMessage(stringValue = apiError.message))
                    }
                }
            }
        }
    }

    private fun saveFilter() {
        viewModelScope.launch {
            when (currentState.fromScreen) {
                FromScreen.Home -> saveFilterFromHomeUseCase.invoke(currentState.adsFilter)
                FromScreen.Category -> saveFilterFromCategoryUseCase.invoke(currentState.adsFilter)
            }
        }
    }

    override fun createInitialState() = FilterUiState()

    override fun onTriggerEvent(event: FilterUiEvent) {
        when (event) {
            is FilterUiEvent.OnFilterClickType -> {
                viewModelScope.launch {
                    when (event.filterClickType) {
                        FilterClickType.OnFilter -> {
                        }

                        is FilterClickType.OnCategory -> {
                            setState { copy(showCategoryDialog = true) }
                        }

                        is FilterClickType.OnNeighborhood -> {

                        }

                        is FilterClickType.OnParameter -> {
                            when (event.filterClickType.parameter.dataType) {
                                DataType.CheckBoxInput -> {
                                    setState {
                                        copy(
                                            adsFilter = currentState.adsFilter?.copy(
                                                parameters = adsFilter?.parameters?.map {
                                                    if (it.id == event.filterClickType.parameter.id)
                                                        it.copy(answer = it.name)
                                                    else it
                                                }?.toImmutableList()
                                            )
                                        )
                                    }
                                }

                                DataType.FixedOption -> {
                                    setState { copy(showParameterDialog = event.filterClickType.parameter) }
                                }

                                else -> {
                                    setState {
                                        copy(
                                            adsFilter = currentState.adsFilter?.copy(
                                                parameters = adsFilter?.parameters?.map {
                                                    if (it.id == event.filterClickType.parameter.id)
                                                        event.filterClickType.parameter
                                                    else it
                                                }?.toImmutableList()
                                            )
                                        )
                                    }
                                }
                            }

                        }

                        is FilterClickType.OnPrice -> {}

                        is FilterClickType.OnCategoryToShowAds -> {
                            setState {
                                copy(
                                    adsFilter = adsFilter?.copy(category = event.filterClickType.category),
                                    showCategoryDialog = false
                                )
                            }

                            getParameters()
                        }
                    }
                }
            }

            is FilterUiEvent.OnMaxPriceChange -> {
                setState { copy(maxPrice = event.value.toPrice()) }
            }

            is FilterUiEvent.OnMinPriceChange -> {
                setState { copy(maxPrice = event.value.toPrice()) }
            }

            is FilterUiEvent.OnAnswerToParameter -> {
                setState {
                    copy(
                        showParameterDialog = null,
                        adsFilter = adsFilter?.copy(parameters = adsFilter.parameters?.map {
                            if (it.id == event.parameter.id) event.parameter
                            else it
                        }?.toImmutableList())
                    )
                }
            }

            FilterUiEvent.DismissDialog -> {
                setState {
                    copy(
                        showCategoryDialog = false,
                        showParameterDialog = null
                    )
                }
            }

            FilterUiEvent.OnClearFilter -> {
                setState {
                    copy(
                        adsFilter = AdsFilter(category = adsFilter?.category)
                    )
                }
            }

            FilterUiEvent.OnSaveFilter -> {
                saveFilter()
            }
        }
    }


}
