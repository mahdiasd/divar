package com.divar.ui.them

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColor(
    val primaryColor: Color,
    val itemColor: Color,
    val iconColor: Color,
    val textColor: Color,
    val hintColor: Color,
    val titleColor: Color,
    val backgroundColor: Color,
    val errorColor: Color,
    val disableColor: Color,
    val successColor: Color,
)

val LocalColor = staticCompositionLocalOf<AppColor> { throw Exception("") }