package com.divar.create_ads

import androidx.lifecycle.viewModelScope
import com.divar.domain.model.onFailure
import com.divar.domain.model.onSuccess
import com.divar.domain.usecase.category.GetCategoriesUseCase
import com.divar.ui.model.UiMessage
import com.divar.ui.viewmodel.BaseViewModel
import com.divar.utils.findIndex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAdsViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
) : BaseViewModel<CreateAdsUiState, CreateAdsUiEvent>() {
    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase.invoke().collect {
                it.onSuccess { categories ->
                    setState { currentState.copy(allCategories = categories.toImmutableList()) }
                }.onFailure { apiError ->
                    setUiMessage(UiMessage(stringValue = apiError.message))
                }
            }
        }
    }


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

            is CreateAdsUiEvent.OnImageChooser -> {
                setState { copy(imageIndexChooser = event.index) }
            }

            is CreateAdsUiEvent.OmImagePicked -> {
                if (event.uriList.size == 1 && !event.uriList.first().path.isNullOrEmpty()) {
                    setState {
                        copy(
                            createAdsParam = createAdsParam.copy(images = currentState.createAdsParam.images.mapIndexed { index, s ->
                                if (index == currentState.imageIndexChooser) event.uriList.first().path!!
                                else s
                            }.toImmutableList())
                        )
                    }
                } else {
                    val temp = currentState.createAdsParam.images.toMutableList()
                    val filledIndexes: MutableList<Int> = mutableListOf()
                    event.uriList.forEachIndexed { _, uri ->
                        temp.findIndex { it.isEmpty() }?.let {
                            filledIndexes.add(it)
                            temp[it] = uri.path ?: ""
                        } ?: run {
                            temp.forEachIndexed { index, s ->
                                if (index !in filledIndexes) {
                                    temp[index] = uri.path ?: ""
                                }
                            }
                        }
                    }
                }
            }

            is CreateAdsUiEvent.OnTitleChanged -> {
                setState { copy(createAdsParam = createAdsParam.copy(title = event.text)) }
            }
        }
    }

}
