package com.divar.home

import androidx.lifecycle.SavedStateHandle
import com.divar.domain.usecase.ads.GetAdsSummaryUseCase
import com.divar.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle?,
    private val getAdsSummaryUseCase: GetAdsSummaryUseCase
) : BaseViewModel<HomeUiState, HomeUiEvent>() {

    override fun createInitialState() = HomeUiState()

    override fun onTriggerEvent(event: HomeUiEvent) {
    }

}
