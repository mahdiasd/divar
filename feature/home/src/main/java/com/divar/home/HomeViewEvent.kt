package com.divar.home

import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.category.Category
import com.divar.domain.model.paginate.Paging
import com.divar.ui.extension.immutableListOf
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val categories: ImmutableList<Category>? = immutableListOf(),
    val showCategories: ImmutableList<Category>? = immutableListOf(),
    val selectedCategories: ImmutableList<Category>? = immutableListOf(),
    val searchedCategories: ImmutableList<Category>? = immutableListOf(),
    val ads: Paging<ImmutableList<AdsSummary>>? = Paging(content = immutableListOf()),
    val page: Int = 0,
    val emptyCategoryCount: Int = 0,
    val categorySearchText: String = ""
) : UiState

sealed class HomeUiEvent : UiEvent {
    data object OnRefreshing : HomeUiEvent()
    data object OnLoadMore : HomeUiEvent()
    data object OnClearCategory : HomeUiEvent()
    data object OnRemoveSelectedCategory : HomeUiEvent()
    data class OnSelectedCategory(val category: Category) : HomeUiEvent()
    data class OnCategorySearchChange(val text: String) : HomeUiEvent()
}

typealias OnAction = (HomeUiEvent) -> Unit