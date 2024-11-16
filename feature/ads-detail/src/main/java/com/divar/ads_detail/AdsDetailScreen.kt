package com.divar.ads_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ads_detail.component.SliderSection
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.ads.Ads
import com.divar.ui.R
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme

@Composable
fun AdsDetailScreen(
    vm: AdsDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val uiState = vm.uiState.collectAsState().value

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.baseModifier(),
            contentAlignment = Alignment.Center
        )
        {
            CircularProgressIndicator(
                modifier = Modifier.size(32.dp),
                strokeWidth = 2.dp,
                color = AppTheme.colors.titleColor
            )
        }
    } else if (uiState.ads == null) {
        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .animateClickable { vm.onTriggerEvent(AdsDetailUiEvent.OnRefresh) },
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.try_again)
        )
    } else {
        AdsDetailScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            onAction = {
                vm.onTriggerEvent(it)
            },
            ads = uiState.ads,
            onBack = onBack
        )
    }

    UiMessageScreen(
        shared = vm.uiMessage
    )
}

@Composable
fun AdsDetailScreenContent(
    modifier: Modifier = Modifier,
    ads: Ads,
    onAction: OnAction,
    onBack: () -> Unit = {}
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        SliderSection(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.3f)
                .background(Color.Gray),
            ads = ads,
            onBack = onBack,
            onAction = onAction
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AdsDetailScreenContent(
        modifier = Modifier.baseModifier(),
        onAction = {},
        ads = FakeData.provideAds()
    )
}
