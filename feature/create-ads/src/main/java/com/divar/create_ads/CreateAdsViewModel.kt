package com.divar.create_ads

import androidx.lifecycle.SavedStateHandle
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAdsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
) : BaseViewModel<CreateAdsUiState, CreateAdsUiEvent>() {

    override fun createInitialState() = CreateAdsUiState()

    override fun onTriggerEvent(event: CreateAdsUiEvent) {
        when (event) {
            CreateAdsUiEvent.DismissDialog -> {
                setState { copy(showCategoryDialog = false) }
            }

            CreateAdsUiEvent.OnNext -> {

            }

            is CreateAdsUiEvent.OnSelectCategory -> {
                setState { copy(createAdsParam = createAdsParam.copy(category = event.category)) }
            }

            CreateAdsUiEvent.ShowCategoryDialog -> {
                setState { copy(showCategoryDialog = true) }
            }
        }
    }

}
