package com.divar.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.category.Category
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.category.GetCategoriesOfAdsUseCase
import com.divar.domain.usecase.filter.ReadFilterFromCategoryUseCase
import com.divar.domain.usecase.filter.ReadFilterFromHomeUseCase
import com.divar.domain.usecase.filter.SaveFilterFromCategoryUseCase
import com.divar.domain.usecase.filter.SaveFilterFromHomeUseCase
import com.divar.domain.usecase.location.GetUserCityUseCase
import com.divar.ui.R
import com.divar.ui.model.FromScreen
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import com.divar.utils.fromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesOfAdsUseCase: GetCategoriesOfAdsUseCase,
    private val getUserCityUseCase: GetUserCityUseCase,
    private val readFilterFromCategoryUseCase: ReadFilterFromCategoryUseCase,
    private val readFilterFromHomeUseCase: ReadFilterFromHomeUseCase,

    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase,
    private val saveFilterFromHomeUseCase: SaveFilterFromHomeUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>() {
    private var searchJob: Job? = null
    private var cityId: Long? = null

    init {
        getInitData()
        getUserCity()
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

    private fun getUserCity() {
        viewModelScope.launch {
            getUserCityUseCase.invoke().collect {
                it.onSuccess {
                    cityId = it.id
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    override fun createInitialState() = SearchUiState()

    override fun onTriggerEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnChangeText -> {
                searchJob?.cancel()
                setState { copy(adsFilter = adsFilter?.copy(searchText = event.searchText)) }
                searchJob = viewModelScope.launch {
                    delay(1500)
                    getCategoriesOfAds()
                }
            }

            is SearchUiEvent.OnSelect -> {
                setState {
                    copy(
                        selectedCategoryOfAds = event.categoryOfAds,
                        adsFilter = adsFilter?.copy(
                            category = Category(
                                name = event.categoryOfAds.categoryName,
                                id = event.categoryOfAds.categoryId,
                                icon = "",
                                children = listOf()
                            )
                        )
                    )
                }
                saveFilter()
            }

            SearchUiEvent.OnRefresh -> getCategoriesOfAds()
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

    private fun getCategoriesOfAds() {
        if (currentState.adsFilter?.searchText.isNullOrEmpty()) return
        if (cityId == null) {
            setUiMessage(UiMessage(intValue = R.string.please_choose_city))
            return
        }
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            getCategoriesOfAdsUseCase.invoke(currentState.adsFilter?.searchText ?: "", cityId!!).collect {
                it.onSuccess {
                    setState { copy(isLoading = false, categoriesOfAds = it.toImmutableList()) }
                }.onFailure { apiError ->
                    setState { copy(isLoading = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }
}
