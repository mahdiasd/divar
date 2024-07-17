package com.divar.ui.core.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.divar.ui.them.AppTheme
import kotlinx.collections.immutable.ImmutableList

@Preview
@Composable
private fun Prev() {
    AppTheme {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.backgroundColor)) {
            LabelSmallText(text = "this is LabelLargeText preview")
            LabelMediumText(text = "this is LabelMediumText preview")
        }
    }
}

@Composable
fun LabelSmallText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.labelSmall,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textColor
) {
    Text(modifier = modifier, text = text, style = textStyle.copy(textAlign = textAlign, color = color))
}

@Composable
fun LabelMediumText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.labelMedium,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textColor
) {
    Text(modifier = modifier, text = text, style = textStyle.copy(textAlign = textAlign, color = color))
}

@Composable
fun LabelMediumColoredText(
    modifier: Modifier = Modifier,
    texts: ImmutableList<String>,
    colors: ImmutableList<Color>,
    textStyle: TextStyle = AppTheme.typography.labelMedium,
) {
    Text(
        modifier = modifier,
        style = textStyle,
        text = buildAnnotatedString {
            texts.forEachIndexed { index, text ->
                withStyle(style = SpanStyle(color = colors[index])) {
                    append(text)
                }
            }
        },
    )
}

