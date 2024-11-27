package com.divar.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.parameter.DataType
import com.divar.ui.core.filter_item.FilterItem
import com.divar.ui.R
import com.divar.ui.category.CategoryDialog
import com.divar.ui.core.button.AppButton
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.domain.model.filter.FilterClickType
import com.divar.ui.model.FromScreen
import com.divar.ui.parameter_dialog.ParameterDialog
import com.divar.ui.them.AppTheme

@Composable
fun FilterScreen(
    vm: FilterViewModel = hiltViewModel(),
    onSaveFilter: (FromScreen) -> Unit,
    onBack: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    FilterScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        onBack = onBack,
        adsFilter = uiState.adsFilter,
        onClearFilters = {
            vm.onTriggerEvent(FilterUiEvent.OnClearFilter)
            vm.onTriggerEvent(FilterUiEvent.OnSaveFilter)
            onSaveFilter(uiState.fromScreen)
        },
        maxPrice = uiState.maxPrice,
        minPrice = uiState.minPrice,
        onSaveFilter = {
            vm.onTriggerEvent(FilterUiEvent.OnSaveFilter)
            onSaveFilter(uiState.fromScreen)
        },
        onAction = { vm.onTriggerEvent(it) }
    )

    if (uiState.showCategoryDialog) {
        CategoryDialog(
            modifier = Modifier,
            categories = uiState.allCategories,
            onDismiss = { vm.onTriggerEvent(FilterUiEvent.DismissDialog) },
            onShowAds = {
                vm.onTriggerEvent(FilterUiEvent.OnFilterClickType(FilterClickType.OnCategoryToShowAds(it)))
            }
        )
    }

    if (uiState.showParameterDialog != null) {
        ParameterDialog(
            modifier = Modifier,
            parameter = uiState.showParameterDialog,
            onDismiss = { vm.onTriggerEvent(FilterUiEvent.DismissDialog) },
            onSelect = { vm.onTriggerEvent(FilterUiEvent.OnAnswerToParameter(it)) }
        )
    }

    UiMessageScreen(shared = vm.uiMessage)
}


@Composable
fun FilterScreenContent(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onClearFilters: () -> Unit = {},
    adsFilter: AdsFilter? = null,
    onAction: OnAction = {},
    onSaveFilter: () -> Unit = {},
    minPrice: String = "",
    maxPrice: String = ""
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.itemColor)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            LabelMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateClickable(onClearFilters)
                    .weight(1f),
                text = stringResource(id = R.string.remove_filters),
                textAlign = TextAlign.End
            )

            BodyMediumText(
                text = stringResource(id = R.string.filters),
                color = AppTheme.colors.titleColor
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .animateClickable(onBack),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "back icon",
                tint = AppTheme.colors.hintColor
            )

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {
            item {
                FilterItem(
                    title = stringResource(id = R.string.category),
                    value = adsFilter?.category?.name,
                    onClick = { onAction(FilterUiEvent.OnFilterClickType(FilterClickType.OnCategory(false))) }
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 0.5.dp
                )
            }

            item {
                FilterItem(
                    title = stringResource(id = R.string.neighborhood),
                    value = adsFilter?.neighborhood?.name,
                    onClick = { onAction(FilterUiEvent.OnFilterClickType(FilterClickType.OnNeighborhood(false))) }
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    thickness = 0.5.dp
                )
            }
            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
                ) {
                    BodyMediumText(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.price_in_toman)
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(0.dp, alignment = Alignment.CenterHorizontally)
                    ) {
                        AppTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            value = maxPrice,
                            onValueChange = { onAction(FilterUiEvent.OnMaxPriceChange(it)) },
                            hint = stringResource(id = R.string.to)
                        )
                        AppTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            value = minPrice,
                            onValueChange = { onAction(FilterUiEvent.OnMinPriceChange(it)) },
                            hint = stringResource(id = R.string.from)
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 0.5.dp
                    )
                }
            }

            adsFilter?.parameters?.let { params ->
                items(items = params)
                { parameter ->
                    when (parameter.dataType) {
                        DataType.CheckBoxInput -> {
                            FilterItem(
                                title = parameter.name,
                                value = !parameter.answer.isNullOrEmpty(),
                                onClick = {
                                    onAction(FilterUiEvent.OnFilterClickType(filterClickType = FilterClickType.OnParameter(parameter, false)))
                                }
                            )
                        }

                        DataType.FixedOption -> {
                            FilterItem(
                                title = parameter.name,
                                value = parameter.answer,
                                onClick = {
                                    onAction(FilterUiEvent.OnFilterClickType(filterClickType = FilterClickType.OnParameter(parameter, false)))
                                }
                            )
                        }

                        else -> {
                            FilterItem(
                                title = parameter.name,
                                value = parameter.answer ?: "",
                                onChangeText = {
                                    onAction(FilterUiEvent.OnFilterClickType(filterClickType = FilterClickType.OnParameter(parameter.copy(answer = it), false)))
                                },
                            )
                        }
                    }
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        thickness = 0.5.dp
                    )
                }
            }
        }

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = R.string.save_filters, onClick = onSaveFilter
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        FilterScreenContent(
            modifier = Modifier.baseModifier(0.dp),
        )
    }
}
