package com.divar.location

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.location.City
import com.divar.location.component.CityItem
import com.divar.ui.R
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.list.SwipeList
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun LocationScreen(
    vm: LocationViewModel = hiltViewModel(),
    onMoveToMain: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.cityIsSelected) {
        if (uiState.cityIsSelected) {
            onMoveToMain()
        }
    }
    LocationScreenContent(
        modifier = Modifier.baseModifier(padding = 0.dp),
        cities = uiState.cities,
        searchText = uiState.searchText,
        isRefreshing = uiState.isLoading,
        onAction = { vm.onTriggerEvent(it) }
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreenContent(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean = false,
    cities: ImmutableList<City>?,
    searchText: String,
    onAction: OnAction
) {
    var isSearching by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.itemColor)
                .heightIn(min = 70.dp)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            if (!isSearching) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .animateClickable { isSearching = true },
                    imageVector = Icons.Default.Search,
                    contentDescription = "search on cities",
                    tint = AppTheme.colors.iconColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .border(1.dp, color = AppTheme.colors.iconColor, shape = CircleShape)
                        .padding(4.dp)
                        .animateClickable { },
                    imageVector = Icons.Default.QuestionMark,
                    contentDescription = "search on cities",
                    tint = AppTheme.colors.iconColor
                )
                BodyMediumText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    text = stringResource(id = R.string.choose_your_city)
                )
            } else {
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchText,
                    onValueChange = { onAction(LocationUiEvent.OnSearch(it)) },
                    hint = stringResource(id = R.string.search_on_cities),
                    icon = Icons.Default.ArrowForward,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = AppTheme.colors.primaryColor,
                        unfocusedTextColor = AppTheme.colors.hintColor,
                        disabledTextColor = AppTheme.colors.hintColor,
                        focusedBorderColor = AppTheme.colors.itemColor,
                        unfocusedBorderColor = AppTheme.colors.itemColor,
                        disabledBorderColor = AppTheme.colors.itemColor,
                        focusedContainerColor = AppTheme.colors.itemColor,
                        unfocusedContainerColor = AppTheme.colors.itemColor,
                        disabledContainerColor = AppTheme.colors.itemColor,
                    ),
                    iconModifier = Modifier
                        .size(24.dp)
                        .animateClickable { isSearching = false }
                )
            }
        }


        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isRefreshing = isRefreshing,
            isLoadMore = false,
            listSize = cities?.size,
            onRefresh = { onAction(LocationUiEvent.OnRefresh) },
            onLoadMore = { }
        ) { index ->
            cities?.get(index)?.let { city ->
                CityItem(
                    city = city,
                    onClick = { onAction(LocationUiEvent.OnCity(city)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                if (index != cities.size - 1) {
                    HorizontalDivider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 0.5.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        LocationScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            cities = FakeData.provideCities().toImmutableList(),
            searchText = "",
            onAction = {}
        )
    }
}
