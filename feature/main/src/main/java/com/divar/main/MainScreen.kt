package com.divar.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier

@Composable
fun MainScreen(
    vm: MainViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value

    MainScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        BodyMediumText(text = "Main Screen")
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    MainScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
