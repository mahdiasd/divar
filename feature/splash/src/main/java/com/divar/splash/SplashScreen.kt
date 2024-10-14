package com.divar.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier
import com.divar.ui.extension.getActivity


@Composable
fun SplashScreen(
    vm: SplashViewModel = hiltViewModel()
) {
    val uiState = vm.uiState.collectAsState().value
    LocalContext.current.getActivity()?.let {
        val splashAi = it.installSplashScreen()
        splashAi.setKeepOnScreenCondition {
            uiState.userIsSelectedCity == null
        }
    }
    SplashScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {


}


@PreviewLightDark
@Composable
private fun Preview() {
    SplashScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
