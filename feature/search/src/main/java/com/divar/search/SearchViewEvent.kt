package com.divar.search

import androidx.compose.runtime.Stable
import com.divar.domain.model.category.CategoryOfAds
import com.divar.ui.extension.immutableListOf
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class SearchUiState(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val categoriesOfAds: ImmutableList<CategoryOfAds> = immutableListOf(),
    val selectedCategoryOfAds: CategoryOfAds? = null
) : UiState


sealed class SearchUiEvent : UiEvent {
    data object OnRefresh : SearchUiEvent()
    data class OnChangeText(val searchText: String) : SearchUiEvent()
    data class OnSelect(val categoryOfAds: CategoryOfAds) : SearchUiEvent()
}

typealias OnAction = (SearchUiEvent) -> Unit