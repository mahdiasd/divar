package com.divar.ads_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.divar.ads_detail.OnAction
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.ads.Ads
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import com.divar.utils.coilRounded

@Composable
fun SliderSection(
    modifier: Modifier,
    ads: Ads,
    onAction: OnAction
) {
    val pagerState = rememberPagerState {
        ads.images.size
    }
    Box(modifier = modifier)
    {
        HorizontalPager(state = pagerState) { pageIndex ->
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = coilRounded(
                    data = ads.images[pageIndex].path,
                    radiusInDp = 0f
                ),
                contentDescription = ""
            )
        }

        Icon(
            modifier = Modifier
                .size(24.dp)
                .align(alignment = Alignment.TopEnd),
            imageVector = Icons.Default.ArrowForward,
            contentDescription = "Click to back",
            tint = AppTheme.colors.iconColor
        )

        Row(
            Modifier
                .align(alignment = Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.Share,
                contentDescription = "",
                tint = AppTheme.colors.iconColor
            )

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.Print,
                contentDescription = "",
                tint = AppTheme.colors.iconColor
            )

            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "",
                tint = AppTheme.colors.iconColor
            )
        }


        Row(
            modifier = Modifier.background(
                color = Color.Black.copy(alpha = 0.4f),
                shape = AppTheme.shapes.roundMedium
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Fullscreen,
                contentDescription = ""
            )
            BodyMediumText(text = ads.images.size.toString())
        }

        HorizontalPagerIndicator(
            modifier = Modifier.fillMaxWidth(),
            pagerState
        )
    }
}

@Composable
fun HorizontalPagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState
) {

    LazyRow(modifier = modifier) {
        items(pagerState.pageCount) { index ->
            Spacer(
                modifier = Modifier
                    .then(
                        if (index == pagerState.currentPage) {
                            Modifier
                                .background(color = Color.White, shape = CircleShape)
                        } else {
                            Modifier
                                .background(color = Color.White.copy(alpha = 0.6f), shape = CircleShape)
                        }
                    )
            )
        }
    }

}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        Box(Modifier.baseModifier(0.dp))
        {
            SliderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.3f)
                    .background(Color.Gray, shape = AppTheme.shapes.roundSmall),
                FakeData.provideAds(),
                onAction = {})
        }
    }
}