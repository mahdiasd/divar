package com.divar.ui.core.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.divar.ui.them.AppTheme


@PreviewLightDark
@Composable
private fun Prev() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor)
        ) {
            BodyLargeText(text = "this is BodyLargeText preview")
            BodyMediumText(text = "this is BodyMediumText preview")
        }
    }
}

@Composable
fun BodyLargeText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.bodyLarge,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textColor,
    minLines: Int = 1
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle.copy(textAlign = textAlign, color = color),
        minLines = minLines
    )
}

@Composable
fun BodyMediumText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textColor,
    textDirection: TextDirection = TextDirection.Rtl,
) {
    Text(modifier = modifier, text = text, style = textStyle.copy(textAlign = textAlign, color = color, textDirection = textDirection))
}


