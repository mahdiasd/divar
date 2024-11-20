package com.divar.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.R
import com.divar.ui.core.text.BodyLargeText
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.core.text.TitleMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme

@Composable
fun AuthScreen(
    vm: AuthViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value

    AuthScreenContent(
        Modifier.baseModifier(0.dp),
        screenMode = uiState.screenMode,
        onAction = { vm.onTriggerEvent(it) }
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun AuthScreenContent(
    modifier: Modifier = Modifier,
    screenMode: ScreenMode = ScreenMode.Login,
    onAction: OnAction,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.itemColor)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
        ) {
            TitleMediumText(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .animateClickable {
                        onAction(
                            AuthUiState.OnChangeMode(
                                when (screenMode) {
                                    ScreenMode.Login -> ScreenMode.Register
                                    ScreenMode.Register -> ScreenMode.Login
                                }
                            )
                        )
                    },
                textAlign = TextAlign.End,
                text = stringResource(
                    id = when (screenMode) {
                        ScreenMode.Login -> R.string.register
                        ScreenMode.Register -> R.string.login
                    }
                ),
                color = AppTheme.colors.primaryColor
            )

            TitleMediumText(
                text = stringResource(
                    id = when (screenMode) {
                        ScreenMode.Login -> R.string.login_to_account
                        ScreenMode.Register -> R.string.register_user
                    }
                )
            )

            Icon(
                modifier = Modifier.size(24.dp),
                tint = AppTheme.colors.iconColor,
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "back"
            )

        }

        TitleMediumText(text = stringResource(id = R.string.enter_mobile_number))
        LabelMediumText(
            text = stringResource(id = R.string.enter_mobile_number_description),
            color = AppTheme.colors.disableColor
        )
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        AuthScreenContent(
            modifier = Modifier.baseModifier(0.dp),
            onAction = {}
        )
    }
}
