package com.divar.filter

import androidx.compose.runtime.Stable
import com.divar.domain.model.category.Category
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.parameter.Parameter
import com.divar.ui.extension.immutableListOf
import com.divar.domain.model.filter.FilterClickType
import com.divar.ui.model.FromScreen
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class FilterUiState(
    val isLoading: Boolean = true,
    val adsFilter: AdsFilter? = null,
    val filterClickType: FilterClickType? = null,
    val minPrice: String = "",
    val maxPrice: String = "",

    val showCategoryDialog: Boolean = false,
    val showParameterDialog: Parameter? = null,

    val allCategories: ImmutableList<Category> = immutableListOf(),

    val fromScreen: FromScreen = FromScreen.Home
) : UiState


sealed class FilterUiEvent : UiEvent {
    data class OnFilterClickType(val filterClickType: FilterClickType) : FilterUiEvent()
    data class OnMaxPriceChange(val value: String) : FilterUiEvent()
    data class OnMinPriceChange(val value: String) : FilterUiEvent()

    // when user select an option in parameter dialog
    data class OnAnswerToParameter(val parameter: Parameter) : FilterUiEvent()

    data object DismissDialog : FilterUiEvent()

    data object OnClearFilter : FilterUiEvent()
    data object OnSaveFilter : FilterUiEvent()
}

typealias OnAction = (FilterUiEvent) -> Unit
