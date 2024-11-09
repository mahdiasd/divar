package com.divar.ui.category

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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.divar.domain.model.category.Category
import com.divar.ui.R
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme
import com.divar.utils.dLog
import com.divar.utils.svgCoil
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDialog(
    modifier: Modifier,
    categories: ImmutableList<Category>,
    onDismiss: () -> Unit,
    onShowAds: (Category) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    val selected = remember { mutableStateListOf<Category>() }

    val showList by remember {
        derivedStateOf {
            if (searchText.isNotEmpty()) {
                findCategoriesByName(categories, searchText)
            } else if (selected.isNotEmpty()) {
                selected.last().children.toImmutableList()
            } else {
                categories
            }
        }
    }

    var isFocused by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = isFocused)

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) sheetState.expand()
    }


    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = AppTheme.colors.backgroundColor,
        onDismissRequest = {
            if (isFocused) isFocused = false
            else onDismiss()
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
            showList = showList,
            selectedCategories = selected.toImmutableList(),
            onSearchChange = { searchText = it },
            onSelectedCategory = {
                if (it.children.isNotEmpty())
                    selected.add(it)
                else onShowAds(it)
            },
            onRemoveLastSelected = {
                selected.removeLastOrNull()
            }
        )
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CategoriesDialogContent(
    modifier: Modifier,
    showList: ImmutableList<Category>,
    selectedCategories: ImmutableList<Category>,
    isFocused: Boolean = false,
    onChangeFocused: (Boolean) -> Unit = {},
    onSearchChange: (String) -> Unit,
    onSelectedCategory: (Category) -> Unit,
    searchText: String = "",
    onRemoveLastSelected: () -> Unit
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
                    .animateClickable(onRemoveLastSelected)
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
            onValueChange = { onSearchChange(it) },
            hint = stringResource(id = R.string.search_on_categories),
            icon = Icons.Outlined.Search,
            iconModifier = Modifier.size(24.dp)
        )

        if (!showList.isEmpty() && !isFocused) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(showList.size) { index ->
                    CategoryDialogItem(
                        category = showList[index],
                        onClick = {
                            onSelectedCategory(showList[index])
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CategoryDialogItem(category: Category, onClick: () -> Unit) {
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


private fun findCategoriesByName(categories: List<Category>?, searchText: String): ImmutableList<Category> {
    val temp = mutableListOf<Category>()

    categories?.forEach { category ->
        if (category.name.contains(searchText, ignoreCase = true)) {
            temp.add(category)
        }
        if (category.children.isNotEmpty()) {
            temp.addAll(findCategoriesByName(category.children, searchText))
        }
    }

    return temp.toImmutableList()
}