package com.divar.create_ads

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.category.Category
import com.divar.domain.model.parameter.Parameter
import com.divar.ui.extension.immutableListOf
import com.divar.ui.viewmodel.UiEvent
import com.divar.ui.viewmodel.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Stable
data class CreateAdsUiState(
    val isLoading: Boolean = true,
    val screenStep: ScreenStep = ScreenStep.Step1,

    val showCategoryDialog: Boolean = false,
    val allCategories: ImmutableList<Category> = immutableListOf(),

    val createAdsParam: CreateAdsParam = CreateAdsParam(),

    val imageIndexChooser: Int? = null,
    val parameters: List<Parameter>? = null,

    val showParameterDialog: Parameter? = null,

) : UiState

enum class ScreenStep { Step1, Step2 }

sealed class CreateAdsUiEvent : UiEvent {
    data object OnNext : CreateAdsUiEvent()
    data object DismissDialog : CreateAdsUiEvent()
    data object ShowCategoryDialog : CreateAdsUiEvent()
    data class OnSelectCategory(val category: Category) : CreateAdsUiEvent()
    data class OnImageChooser(val index: Int) : CreateAdsUiEvent()
    data class OmImagePicked(val pathList: List<String>) : CreateAdsUiEvent()

    data class OnTitleChanged(val text: String) : CreateAdsUiEvent()
    data class OnDescriptionChanged(val text: String) : CreateAdsUiEvent()

    data object OnNeighborhood : CreateAdsUiEvent()
    data class OnPriceChanged(val text: String) : CreateAdsUiEvent()

    data class OnParameter(val parameter: Parameter) : CreateAdsUiEvent()
    data class OnAnswerToParameter(val parameter: Parameter) : CreateAdsUiEvent()

}

typealias OnAction = (CreateAdsUiEvent) -> Unit
