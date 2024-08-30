package com.divar.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier


@Composable
fun HomeScreen(
    vm: HomeViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value

    HomeScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    HomeScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
