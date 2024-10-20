package com.divar.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.domain.model.category.CategoryOfAds
import com.divar.search.component.CategoryOfAdsItem
import com.divar.ui.R
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.list.SwipeList
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.extension.immutableListOf
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchScreen(
    vm: SearchViewModel = hiltViewModel(),
    onSelected: (CategoryOfAds) -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    LaunchedEffect(key1 = uiState.selectedCategoryOfAds) {
        if (uiState.selectedCategoryOfAds != null) {
            onSelected(uiState.selectedCategoryOfAds)
        }
    }
    SearchScreenContent(
        Modifier.baseModifier(0.dp),
        searchText = uiState.searchText,
        onAction = { vm.onTriggerEvent(it) },
        list = uiState.categoriesOfAds,
        isLoading = uiState.isLoading,
        onBack = {}
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreenContent(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onAction: OnAction = {},
    list: ImmutableList<CategoryOfAds> = immutableListOf<CategoryOfAds>(),
    isLoading: Boolean = false,
    onBack: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Box(modifier = Modifier.fillMaxWidth())
        {

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchText,
                onValueChange = {
                    onAction(SearchUiEvent.OnChangeText(it))
                },
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
                hint = stringResource(id = R.string.search),
                icon = Icons.Default.ArrowForward,
                maxLine = 1,
                iconModifier = Modifier
                    .size(24.dp)
                    .animateClickable { onBack() }
            )

            Icon(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .background(AppTheme.colors.titleColor, shape = CircleShape)
                    .padding(4.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "close icon"
            )
        }

        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isRefreshing = isLoading,
            isLoadMore = false,
            listSize = list.size,
            onRefresh = { onAction(SearchUiEvent.OnRefresh) },
            onLoadMore = {}
        ) {
            CategoryOfAdsItem(categoryOfAds = list[it]) {
                onAction(SearchUiEvent.OnSelect(list[it]))
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


@PreviewLightDark
@Composable
private fun Preview() {
    SearchScreenContent(
        modifier = Modifier.baseModifier(), onBack = {}
    )
}
