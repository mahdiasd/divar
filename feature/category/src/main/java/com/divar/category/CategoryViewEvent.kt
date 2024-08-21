package com.divar.category

import androidx.compose.runtime.Stable
import com.divar.domain.model.Category
import com.divar.ui.extension.immutableListOf
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CategoryUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = true,
    val isLoadMore: Boolean = false,
    val categories: ImmutableList<Category>? = immutableListOf(),
    val selectedCategories: ImmutableList<Category> = immutableListOf(),
    val showCategories: ImmutableList<Category>? = immutableListOf(),
    val categoryTitle: String? = null

) : UiState


sealed class CategoryUiEvent : UiEvent {
    data class OnCategorySelected(val category: Category) : CategoryUiEvent()
    data object OnRefresh : CategoryUiEvent()
    data object OnLoadMore : CategoryUiEvent()
}

typealias OnAction = (CategoryUiEvent) -> Unit

