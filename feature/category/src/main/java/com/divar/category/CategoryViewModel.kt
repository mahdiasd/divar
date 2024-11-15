package com.divar.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.category.Category
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.category.GetCategoriesUseCase
import com.divar.domain.usecase.filter.SaveFilterFromCategoryUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import com.divar.utils.dLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveFilterFromCategoryUseCase: SaveFilterFromCategoryUseCase
) : BaseViewModel<CategoryUiState, CategoryUiEvent>() {

    init {
        getCategories()
    }

    private fun getCategories() {
        setState { copy(isRefreshing = true) }
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess {
                    setState {
                        currentState.copy(
                            isRefreshing = false,
                            categories = it.toImmutableList(),
                        )
                    }
                    handleShowingCategory()
                }.onFailure { apiError ->
                    setState { copy(isRefreshing = false) }
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }

    override fun createInitialState() = CategoryUiState()

    override fun onTriggerEvent(event: CategoryUiEvent) {
        when (event) {
            is CategoryUiEvent.OnCategorySelected -> {
                if (event.category.children.isNotEmpty()) {
                    val newList = currentState.selectedCategories.toMutableList()
                    newList.add(event.category)
                    setState { copy(selectedCategories = newList.toImmutableList()) }
                    handleShowingCategory()
                } else {
                    setState { copy(selectedCategory = event.category) }
                    saveFilter(event.category)
                }
            }

            CategoryUiEvent.OnBackInCategoryDialog -> {
                if (currentState.selectedCategories.isNotEmpty()) {
                    val newList = currentState.selectedCategories.toMutableList()
                    newList.removeLast()
                    setState { copy(selectedCategories = newList.toImmutableList()) }
                    handleShowingCategory()
                }
            }

            CategoryUiEvent.OnLoadMore -> {
            }

            CategoryUiEvent.OnRefresh -> {
                getCategories()
            }

            CategoryUiEvent.OnClearSelectedCategory -> {
                setState { copy(selectedCategory = null) }
            }
        }
    }

    private fun saveFilter(category: Category) {
        viewModelScope.launch {
            saveFilterFromCategoryUseCase.invoke(AdsFilter(category = category))
        }
    }

    private fun handleShowingCategory() {
        if (currentState.selectedCategories.isEmpty()) {
            setState {
                copy(
                    showCategories = currentState.categories,
                    categoryTitle = null
                )
            }
        } else {
            setState {
                copy(
                    showCategories = currentState.selectedCategories.last().children.toImmutableList(),
                    categoryTitle = currentState.selectedCategories.last().name
                )
            }
        }
    }

}
