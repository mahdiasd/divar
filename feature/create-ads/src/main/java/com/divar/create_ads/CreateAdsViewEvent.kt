package com.divar.create_ads

import android.net.Uri
import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.category.Category
import com.divar.ui.extension.immutableListOf
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList

@Stable
data class CreateAdsUiState(
    val isLoading: Boolean = true,
    val screenStep: ScreenStep = ScreenStep.Step1,

    val showCategoryDialog: Boolean = false,
    val allCategories: ImmutableList<Category> = immutableListOf(),

    val createAdsParam: CreateAdsParam = CreateAdsParam(),

    val imageIndexChooser: Int? = null
) : UiState

enum class ScreenStep { Step1, Step2 }

sealed class CreateAdsUiEvent : UiEvent {
    data object OnNext : CreateAdsUiEvent()
    data object DismissDialog : CreateAdsUiEvent()
    data object ShowCategoryDialog : CreateAdsUiEvent()
    data class OnSelectCategory(val category: Category) : CreateAdsUiEvent()
    data class OnImageChooser(val index: Int) : CreateAdsUiEvent()
    data class OmImagePicked(val uriList: List<Uri>) : CreateAdsUiEvent()
}

typealias OnAction = (CreateAdsUiEvent) -> Unit
