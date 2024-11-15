package com.divar.ads

import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.category.Category
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.location.City
import com.divar.domain.model.paginate.Paging
import com.divar.domain.model.parameter.Parameter
import com.divar.ui.extension.immutableListOf
import com.divar.domain.model.filter.FilterClickType
import com.divar.ui.model.FromScreen
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class AdsUiState(
    val isLoading: Boolean = true,
    val isLoadMore: Boolean = false,
    val ads: Paging<ImmutableList<AdsSummary>>? = Paging(content = immutableListOf()),
    val page: Int = 0,
    val userCity: City? = null,
    val adsFilter: AdsFilter? = null,
    val navigateToFilter: FilterClickType? = null,
    val navigateToNeighborhood: Boolean = false,
    val showCategoryDialog: Boolean = false,
    val categories: ImmutableList<Category> = immutableListOf(),
    val fromScreen: FromScreen = FromScreen.Home,
) : UiState


sealed class AdsUiEvent : UiEvent {
    data object OnRefresh : AdsUiEvent()
    data object OnLoadMore : AdsUiEvent()
    data object OnDismissDialog : AdsUiEvent()
    data object OnNavigated : AdsUiEvent()
    data class OnFilterClickType(val filterClickType: FilterClickType) : AdsUiEvent()
}

typealias OnAction = (AdsUiEvent) -> Unit
