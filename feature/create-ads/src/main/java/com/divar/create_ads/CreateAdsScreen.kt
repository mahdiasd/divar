package com.divar.create_ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.create_ads.component.CreateAdsToolbar
import com.divar.ui.R
import com.divar.ui.core.button.AppButton
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme

@Composable
fun CreateAdsScreen(
    vm: CreateAdsViewModel = hiltViewModel(),
    onBack: () -> Unit,
) {
    val uiState = vm.uiState.collectAsState().value

    CreateAdsScreenContent(
        modifier = Modifier.baseModifier(0.dp),
        onAction = { vm.onTriggerEvent(it) },
        onClose = onBack
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun CreateAdsScreenContent(
    modifier: Modifier = Modifier,
    onAction: OnAction,
    onClose: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        containerColor = AppTheme.colors.backgroundColor,
        topBar = {
            CreateAdsToolbar(
                onAction = onAction,
                onClose = onClose
            )
        },
        bottomBar = {
            BottomBar(onAction = onAction)
        }
    ) {
        Spacer(modifier = Modifier.padding(it))
    }

}

@Composable
fun BottomBar(onAction: OnAction) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(16.dp)
    ) {
        AppButton(
            modifier = Modifier
                .height(55.dp)
                .width(180.dp),
            text = R.string.next,
            onClick = {
                onAction(CreateAdsUiEvent.OnNext)
            }
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CreateAdsScreenContent(
            modifier = Modifier.baseModifier(),
            onAction = {}
        )
    }
}
