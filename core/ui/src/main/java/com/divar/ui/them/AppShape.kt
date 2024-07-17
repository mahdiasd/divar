package com.divar.ui.them

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

data class AppShapes(
    val roundExtraSmall: RoundedCornerShape = RoundedCornerShape(4.dp),
    val roundSmall: RoundedCornerShape = RoundedCornerShape(8.dp),
    val roundMedium: RoundedCornerShape = RoundedCornerShape(12.dp),
    val roundLarge: RoundedCornerShape = RoundedCornerShape(24.dp),
    val roundExtraLarge: RoundedCornerShape = RoundedCornerShape(32.dp),
)

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }