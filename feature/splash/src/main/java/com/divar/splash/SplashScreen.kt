package com.divar.splash

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.core.splashscreen.SplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.baseModifier


@Composable
fun SplashScreen(
    splash: SplashScreen,
    vm: SplashViewModel = hiltViewModel()
) {
    val uiState = vm.uiState.collectAsState().value

    splash.setKeepOnScreenCondition {
        uiState.userIsSelectedCity == null
    }

    SplashScreenContent(Modifier.baseModifier())

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
private fun SplashScreenContent(
    modifier: Modifier = Modifier,
) {


}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

@PreviewLightDark
@Composable
private fun Preview() {
    SplashScreenContent(
        modifier = Modifier.baseModifier(),
    )
}
