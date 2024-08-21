package com.divar.category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.category.component.CategoryItem
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.Category
import com.divar.ui.R
import com.divar.ui.core.list.SwipeList
import com.divar.ui.core.text.TitleMediumText
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.ImmutableList


@Composable
fun CategoryScreen(
    vm: CategoryViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value
    CategoryScreenContent(
        modifier = Modifier
            .baseModifier(0.dp),
        list = uiState.showCategories,
        isRefreshing = uiState.isRefreshing,
        isLoadMore = uiState.isLoadMore,
        categoryTitle = uiState.categoryTitle,
        onAction = { vm.onTriggerEvent(it) }
    )
}

@Composable
fun CategoryScreenContent(
    modifier: Modifier = Modifier,
    list: ImmutableList<Category>?,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    categoryTitle: String? = null,
    onAction: OnAction
) {
    Column(modifier = modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .shadow(1.dp)
                .background(color = AppTheme.colors.itemColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.End)
        ) {
            TitleMediumText(
                text = if (categoryTitle.isNullOrEmpty()) stringResource(id = R.string.ads_category) else categoryTitle
            )

            if (!categoryTitle.isNullOrEmpty()) {
                Spacer(modifier = Modifier.width(12.dp))
                Icon(
                    modifier = Modifier
                        .size(18.dp),
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "back icon",
                    tint = AppTheme.colors.iconColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SwipeList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            isRefreshing = isRefreshing,
            isLoadMore = isLoadMore,
            listSize = list?.size,
            onRefresh = { onAction(CategoryUiEvent.OnRefresh) },
            onLoadMore = { onAction(CategoryUiEvent.OnLoadMore) }
        ) { index: Int ->
            list?.get(index)?.apply {
                CategoryItem(
                    modifier = Modifier.fillMaxWidth(),
                    category = this,
                    onClick = { onAction(CategoryUiEvent.OnCategorySelected(this)) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryScreenContent(
            modifier = Modifier.baseModifier(),
            list = FakeData.provideCategories(),
            isRefreshing = false,
            isLoadMore = false,
            onAction = {}
        )
    }
}
