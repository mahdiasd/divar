package com.divar.ads.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.divar.ads.AdsUiEvent
import com.divar.ads.OnAction
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.filter.AdsFilter
import com.divar.domain.model.filter.FilterClickType
import com.divar.ui.R
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelSmallText
import com.divar.ui.extension.animateClickable

import com.divar.ui.them.AppTheme

@Composable
internal fun AdsToolbar(
    modifier: Modifier = Modifier,
    searchText: String,
    cityName: String,
    onCity: () -> Unit,
    onSearch: () -> Unit,
    onBack: () -> Unit,
    adsFilter: AdsFilter?,
    onAction: OnAction = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(4.dp)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, AppTheme.colors.hintColor, shape = AppTheme.shapes.roundExtraSmall)
                    .padding(4.dp)
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "location icon",
                    tint = AppTheme.colors.hintColor
                )

                LabelSmallText(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .animateClickable(onCity),
                    text = cityName
                )

                VerticalDivider(
                    modifier = Modifier
                        .background(AppTheme.colors.hintColor)
                        .padding(start = 0.dp)
                        .height(20.dp)
                )
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "search icon",
                    tint = AppTheme.colors.hintColor
                )

                BodyMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .animateClickable(onSearch)
                        .padding(top = 4.dp, end = 4.dp),
                    text = searchText.ifEmpty { stringResource(id = R.string.search) },
                    color = AppTheme.colors.hintColor
                )
            }

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .animateClickable(onBack),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "back icon",
                tint = AppTheme.colors.hintColor
            )
        }

        LazyRow(
            reverseLayout = true,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.End),
        ) {
            item {
                AdsFilterItem(
                    title = stringResource(id = R.string.filters),
                    isSelected = adsFilter != null,
                    icon = R.drawable.ic_filter,
                    onClick = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnFilter)) }
                )
            }
            item {
                AdsFilterItem(
                    title = adsFilter?.category?.name ?: stringResource(id = R.string.categories),
                    isVisibleClose = adsFilter?.category?.name != null,
                    icon = R.drawable.ic_category,
                    onClose = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnCategory(true))) },
                    onClick = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnCategory(false))) }
                )
            }
            item {
                AdsFilterItem(
                    title = adsFilter?.neighborhood?.name ?: stringResource(id = R.string.choose_neighborhood),
                    isVisibleClose = adsFilter?.neighborhood?.name != null,
                    onClose = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnNeighborhood(true))) },
                    onClick = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnNeighborhood(false))) }

                )
            }
            item {

                AdsFilterItem(
                    title = adsFilter?.price ?: stringResource(id = R.string.price),
                    isVisibleClose = adsFilter?.price != null,
                    onClose = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnPrice(true))) },
                    onClick = { onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnPrice(false))) }
                )
            }
            adsFilter?.parameters?.takeIf { it.isNotEmpty() }?.let { paramaters ->
                items(items = paramaters)
                { paramater ->
                    AdsFilterItem(
                        title = paramater.answer ?: paramater.name,
                        isVisibleClose = paramater.answer != null,
                        onClose = {
                            onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnParameter(paramater, isRemove = true)))
                        },
                        onClick = {
                            onAction(AdsUiEvent.OnFilterClickType(FilterClickType.OnParameter(paramater, isRemove = false)))
                        }
                    )
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun ToolbarPreview() {
    AppTheme {
        AdsToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            searchText = "آپارتمان",
            cityName = "مشهد",
            onCity = {},
            onSearch = {},
            onBack = {},
            adsFilter = null
        )
    }
}

@PreviewLightDark
@Composable
private fun ToolbarSelectedPreview() {
    AppTheme {
        AdsToolbar(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            searchText = "آپارتمان",
            cityName = "مشهد",
            onCity = {},
            onSearch = {},
            onBack = {},
            adsFilter = AdsFilter(category = FakeData.provideCategories().first())
        )
    }
}
