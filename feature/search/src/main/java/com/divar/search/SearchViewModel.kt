package com.divar.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.category.GetCategoriesOfAdsUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesOfAdsUseCase: GetCategoriesOfAdsUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>() {
    var searchJob: Job? = null

    init {
        getInitData()
    }

    private fun getInitData() {
        savedStateHandle?.get<String>("searchText")?.let {
            setState { copy(searchText = it) }
        }
    }

    override fun createInitialState() = SearchUiState()

    override fun onTriggerEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OnChangeText -> {
                searchJob?.cancel()
                setState { copy(searchText = event.searchText) }
                searchJob = viewModelScope.launch {
                    delay(1500)
                    getCategoriesOfAds()
                }
            }

            is SearchUiEvent.OnSelect -> {
                setState { copy(selectedCategoryOfAds = event.categoryOfAds) }
            }

            SearchUiEvent.OnRefresh -> getCategoriesOfAds()
        }
    }

    private fun getCategoriesOfAds() {
        if (currentState.searchText.isEmpty()) return
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            getCategoriesOfAdsUseCase.invoke(currentState.searchText).collect {
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
