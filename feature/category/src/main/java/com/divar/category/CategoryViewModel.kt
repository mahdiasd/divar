package com.divar.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.GetCategoriesUseCase
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
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<CategoryUiState, CategoryUiEvent>() {

    init {
        getCategories()
    }

    private fun getCategories() {
        setState { copy(isLoading = true) }
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
                    apiError.dLog("")
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

                }
            }

            CategoryUiEvent.OnLoadMore -> {

            }

            CategoryUiEvent.OnRefresh -> {

            }
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
                    showCategories = currentState.selectedCategories.first().children.toImmutableList(),
                    categoryTitle = currentState.selectedCategories.first().name
                )
            }
        }
    }

}
