package com.divar.ui.them

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

object AppTheme {
    val colors: AppColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

private val DarkColorScheme = AppColor(
    primaryColor = PrimaryColorDark,
    itemColor = ItemColorDark,
    iconColor = IconColorDark,
    textColor = TextColorDark,
    hintColor = HintColorDark,
    titleColor = TitleColorDark,
    backgroundColor = BackgroundColorDark,
    errorColor = ErrorColorDark,
    disableColor = DisableColorDark,
    successColor = SuccessColorDark
)

private val LightColorScheme = AppColor(
    primaryColor = PrimaryColorLight,
    itemColor = ItemColorLight,
    iconColor = IconColorLight,
    textColor = TextColorLight,
    hintColor = HintColorLight,
    titleColor = TitleColorLight,
    backgroundColor = BackgroundColorLight,
    errorColor = ErrorColorLight,
    disableColor = DisableColorLight,
    successColor = SuccessColorLight
)

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    CompositionLocalProvider(
        LocalColor provides colorScheme,
        LocalDimensions provides AppTheme.dimensions,
        LocalShapes provides AppTheme.shapes,
        LocalTypography provides AppTheme.typography
    ) {
        content()
    }

}