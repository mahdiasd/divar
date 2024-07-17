package com.divar.ui.them

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.sp
import com.divar.ui.R

private val iranSans = FontFamily(Font(R.font.iran_sans, FontWeight.Normal))
private val iranSansBold = FontFamily(Font(R.font.iran_sans_bold, FontWeight.Bold))

data class AppTypography(
    val titleLarge: TextStyle = TextStyle(
        fontFamily = iranSansBold,
        fontWeight = FontWeight.Bold,
        textDirection = TextDirection.Rtl,
        fontSize = 24.sp,
    ),
    val title: TextStyle = TextStyle(
        fontFamily = iranSansBold,
        textDirection = TextDirection.Rtl,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),

    val bodyMediumBold: TextStyle = TextStyle(
        fontFamily = iranSansBold,
        textDirection = TextDirection.Rtl,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = iranSans,
        fontWeight = FontWeight.Normal,
        textDirection = TextDirection.Rtl,
        fontSize = 18.sp
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = iranSans,
        textDirection = TextDirection.Rtl,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    val labelMedium: TextStyle = TextStyle(
        fontFamily = iranSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textDirection = TextDirection.Rtl,
        lineHeight = 24.sp
    ),

    val labelSmall: TextStyle = TextStyle(
        fontFamily = iranSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        textDirection = TextDirection.Rtl
    )
)

val LocalTypography = staticCompositionLocalOf { AppTypography() }