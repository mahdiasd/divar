package com.divar.home.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.category.Category
import com.divar.home.HomeUiEvent
import com.divar.home.OnAction
import com.divar.ui.R
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import com.divar.utils.dLog
import com.divar.utils.svgCoil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesDialog(
    modifier: Modifier,
    showCategories: ImmutableList<Category>?,
    selectedCategories: ImmutableList<Category>,
    searchedCategories: ImmutableList<Category>?,
    searchText: String,
    onAction: OnAction,
) {
    var isFocused by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = isFocused)

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) sheetState.expand()
    }

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = AppTheme.colors.backgroundColor,
        onDismissRequest = {
            if (isFocused) {
                isFocused = false
            } else onAction(HomeUiEvent.OnClearCategory)
        }) {
        CategoriesDialogContent(
            modifier = Modifier
                .navigationBarsPadding()
                .padding(12.dp)
                .then(modifier)
                .then(
                    if (isFocused) Modifier.fillMaxSize()
                    else Modifier
                ),
            showCategories = showCategories,
            isFocused = isFocused,
            searchedCategories = searchedCategories,
            onChangeFocused = { isFocused = it },
            selectedCategories = selectedCategories,
            searchText = searchText,
            onAction = onAction
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesDialogContent(
    modifier: Modifier,
    showCategories: ImmutableList<Category>?,
    selectedCategories: ImmutableList<Category>,
    searchedCategories: ImmutableList<Category>?,
    isFocused: Boolean = false,
    onChangeFocused: (Boolean) -> Unit = {},
    searchText: String = "",
    onAction: OnAction
) {
    val focusManager = LocalFocusManager.current
    LaunchedEffect(key1 = isFocused) {
        if (!isFocused) focusManager.clearFocus()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Box(modifier = Modifier.fillMaxWidth())
        {
            Icon(
                modifier = Modifier
                    .animateClickable { onAction(HomeUiEvent.OnRemoveSelectedCategory) }
                    .size(24.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "back icon",
                tint = AppTheme.colors.iconColor
            )

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
            ) {
                selectedCategories.reversed().forEachIndexed { index, category ->
                    BodyMediumText(
                        modifier = Modifier,
                        text = category.name,
                        textAlign = TextAlign.Center,
                        color = if (index == 0) AppTheme.colors.titleColor
                        else AppTheme.colors.disableColor
                    )
                    if (index != selectedCategories.size - 1) {
                        Icon(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .size(12.dp),
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back icon",
                            tint = AppTheme.colors.disableColor
                        )
                    }
                }
            }

        }

        HorizontalDivider()

        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    it.isFocused.dLog("onFocusChanged")
                    onChangeFocused(it.isFocused)
                },
            value = searchText,
            onValueChange = { onAction(HomeUiEvent.OnCategorySearchChange(it)) },
            hint = stringResource(id = R.string.search_on_categories),
            icon = Icons.Outlined.Search,
            iconModifier = Modifier.size(24.dp)
        )

        showCategories.dLog("")



        if (!showCategories.isNullOrEmpty() && !isFocused) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(showCategories.size) { index ->
                    CategoryDialogItem(
                        category = showCategories[index],
                        onClick = {
                            if (showCategories[index].children.isNotEmpty()) {
                                onAction(HomeUiEvent.OnSelectedCategory(category = showCategories[index]))
                            }
                        }
                    )
                }
            }
        } else if (isFocused && !searchedCategories.isNullOrEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(searchedCategories.size) { index ->
                    CategoryDialogItem(
                        category = searchedCategories[index],
                        onClick = {
                            TODO("show ads when click")
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun CategoryDialogItem(category: Category, onClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .animateClickable(onClick)
            .background(AppTheme.colors.itemColor, shape = AppTheme.shapes.roundSmall)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        if (category.children.isNotEmpty()) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                tint = AppTheme.colors.iconColor
            )
        }

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
                .weight(1f),
            text = category.name
        )

        category.icon.takeIf { it.isNotBlank() }?.let { icon ->
            AsyncImage(
                modifier = Modifier.size(20.dp),
                model = svgCoil(icon),
                contentDescription = "category icon",
                colorFilter = ColorFilter.tint(color = AppTheme.colors.iconColor),
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        Box(modifier = Modifier.baseModifier()) {
            CategoriesDialogContent(
                modifier = Modifier,
                showCategories = FakeData.provideCategories().first().children.toImmutableList(),
                selectedCategories = FakeData.provideCategories().first().children.toImmutableList(),
                searchedCategories = null
            ) {

            }
        }
    }
}