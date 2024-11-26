package com.divar.create_ads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.R
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier

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
        topBar = {
            CreateAdsToolbar(
                onAction = onAction,
                onClose = onClose
            )
        },
        bottomBar = {

        }
    ) {
        Spacer(modifier = Modifier.padding(it))
    }

}

@Composable
fun CreateAdsToolbar(
    onAction: OnAction,
    onClose : () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.Refresh,
            contentDescription = ""
        )

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.insert_ads)
        )

        Icon(
            modifier = Modifier
                .size(24.dp)
                .animateClickable { onClose() },
            imageVector = Icons.Default.Close,
            contentDescription = ""
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    CreateAdsScreenContent(
        modifier = Modifier.baseModifier(),
        onAction = {}
    )
}
