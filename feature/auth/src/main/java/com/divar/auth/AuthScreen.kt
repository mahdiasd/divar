package com.divar.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.divar.ui.R
import com.divar.ui.core.button.AppButton
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.text.LabelMediumColoredText
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.core.text.TitleMediumText
import com.divar.ui.core.ui_message.UiMessageScreen
import com.divar.ui.extension.animateClickable
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.toImmutableList

@Composable
fun AuthScreen(
    vm: AuthViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState().value

    AuthScreenContent(
        Modifier.baseModifier(0.dp),
        screenMode = uiState.screenMode,
        onAction = { vm.onTriggerEvent(it) },
        mobile = uiState.mobile,
        password = uiState.password,
        repeatPassword = uiState.repeatPassword
    )

    UiMessageScreen(shared = vm.uiMessage)
}

@Composable
fun AuthScreenContent(
    modifier: Modifier = Modifier,
    screenMode: ScreenMode = ScreenMode.Login,
    onAction: OnAction,
    mobile: String = "",
    password: String = "",
    repeatPassword: String = "",
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
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
                            AuthUiEvent.OnChangeMode(
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, alignment = Alignment.Top)
        ) {
            TitleMediumText(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = stringResource(id = R.string.enter_mobile_number)
            )

            LabelMediumText(
                text = stringResource(id = R.string.enter_mobile_number_description),
                color = AppTheme.colors.disableColor
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = mobile,
                onValueChange = { onAction(AuthUiEvent.OnTextChanged(TypingType.Mobile(it))) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                hint = stringResource(id = R.string.mobile_number)
            )

            AppTextField(
                modifier = Modifier.fillMaxWidth(),
                value = password,
                onValueChange = { onAction(AuthUiEvent.OnTextChanged(TypingType.Password(it))) },
                hint = stringResource(id = R.string.password)
            )

            androidx.compose.animation.AnimatedVisibility(visible = screenMode == ScreenMode.Register) {
                AppTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = repeatPassword,
                    onValueChange = { onAction(AuthUiEvent.OnTextChanged(TypingType.RepeatPassword(it))) },
                    hint = stringResource(id = R.string.repeat_password)
                )
            }

            LabelMediumColoredText(
                modifier = Modifier.fillMaxWidth(),
                texts = listOf(
                    stringResource(id = R.string.auth_tip_section1),
                    " ${stringResource(id = R.string.auth_tip_section2)}",
                    " ${stringResource(id = R.string.auth_tip_section3)}",
                    " ${stringResource(id = R.string.auth_tip_section4)}",
                    " ${stringResource(id = R.string.auth_tip_section5)}",
                ).toImmutableList(),
                links = listOf(
                    "",
                    "https://divar.ir",
                    "",
                    "https://divar.ir",
                    ""
                ).toImmutableList(),
                colors = listOf(
                    AppTheme.colors.disableColor,
                    AppTheme.colors.primaryColor,
                    AppTheme.colors.disableColor,
                    AppTheme.colors.primaryColor,
                    AppTheme.colors.disableColor,
                ).toImmutableList(),
            )
        }

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.itemColor)
                .padding(16.dp),
            text = when (screenMode) {
                ScreenMode.Login -> R.string.login
                ScreenMode.Register -> R.string.register
            },
            onClick = { onAction(AuthUiEvent.OnBtnClick) }
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
