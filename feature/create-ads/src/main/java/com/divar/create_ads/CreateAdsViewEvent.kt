package com.divar.create_ads

import android.net.Uri
import android.os.Build
import androidx.compose.runtime.Stable
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.category.Category
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
    val permissions: ImmutableList<String> = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) listOf(android.Manifest.permission.READ_MEDIA_IMAGES).toImmutableList()
    else listOf(android.Manifest.permission.READ_EXTERNAL_STORAGE).toImmutableList()
) : UiState

enum class ScreenStep { Step1, Step2 }

sealed class CreateAdsUiEvent : UiEvent {
    data object OnNext : CreateAdsUiEvent()
    data object DismissDialog : CreateAdsUiEvent()
    data object ShowCategoryDialog : CreateAdsUiEvent()
    data class OnSelectCategory(val category: Category) : CreateAdsUiEvent()
    data class OnImageChooser(val index: Int) : CreateAdsUiEvent()
    data class OmImagePicked(val uriList: List<Uri>) : CreateAdsUiEvent()

    data class OnTitleChanged(val text: String) : CreateAdsUiEvent()
}

typealias OnAction = (CreateAdsUiEvent) -> Unit
