package com.divar.ui.core.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.divar.ui.extension.OnBottomReached
import com.divar.ui.them.AppTheme

@ExperimentalMaterial3Api
@Composable
fun SwipeList(
    modifier: Modifier,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    boxState: PullToRefreshState = rememberPullToRefreshState(),
    lazyState: LazyListState = rememberLazyListState(),
    listSize: Int?,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    key: ((Int) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(bottom = 48.dp),
    screenToShow: @Composable (index: Int) -> Unit
) {
    val showEmptyVector = ((!isLoadMore && !isRefreshing) && listSize != null && listSize == 0)
    val showErrorVector = ((!isLoadMore && !isRefreshing) && listSize == null)

    PullToRefreshBox(
        modifier = modifier,
        isRefreshing = isRefreshing,
        state = boxState,
        onRefresh = onRefresh
    ) {
        if (showEmptyVector) {
//                EmptyScreen(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.Center)
//                )
        }

        LazyColumn(
            state = lazyState,
            contentPadding = contentPadding,
            modifier = Modifier
                .clipScrollableContainer(Orientation.Vertical)
                .align(Alignment.TopCenter)
        ) {
            items(listSize ?: 0, key = key) { index ->
                screenToShow(index)
            }
        }

        if (isLoadMore) {
            CircularProgressIndicator(
                modifier = Modifier
                    .shadow(2.dp, CircleShape, spotColor = AppTheme.colors.primaryColor)
                    .align(Alignment.BottomCenter)
                    .size(size = 32.dp)
                    .padding(4.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(bottom = 8.dp),
                strokeWidth = 3.dp,
                color = AppTheme.colors.primaryColor
            )
        }


        if (showErrorVector) {
//                FailedScreen(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .align(Alignment.Center),
//                    onRetry = onRefresh
//                )
        }
    }

    lazyState.OnBottomReached {
        onLoadMore.invoke()
    }
}