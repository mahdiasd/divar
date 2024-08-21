package com.divar.ui.core.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.divar.ui.extension.OnBottomReached
import com.divar.ui.them.AppTheme
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun SwipeList(
    modifier: Modifier,
    isRefreshing: Boolean,
    isLoadMore: Boolean,
    listSize: Int?,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
    key: ((Int) -> Any)? = null,
    contentPadding: PaddingValues = PaddingValues(bottom = 48.dp),
    screenToShow: @Composable (index: Int) -> Unit
) {
    val showEmptyVector = ((!isLoadMore && !isRefreshing) && listSize != null && listSize == 0)
    val showErrorVector = ((!isLoadMore && !isRefreshing) && listSize == null)

    val listState = rememberLazyListState()
    SwipeRefresh(
        modifier = modifier,
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            onRefresh.invoke()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp)
        ) {
            if (showEmptyVector) {
//                EmptyScreen(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.Center)
//                )
            }

            LazyColumn(
                state = listState,
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
    }

    listState.OnBottomReached {
        onLoadMore.invoke()
    }
}