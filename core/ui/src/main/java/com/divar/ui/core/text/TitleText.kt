package com.divar.ui.core.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
        Column {
            TitleLargeText(text = "this is TitleLargeText preview")
            TitleMediumText(text = "this is TitleMediumText preview")
        }
    }
}

@Composable
fun TitleLargeText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.titleLarge,
    textAlign: TextAlign = TextAlign.Start,
    color: Color = AppTheme.colors.textColor,
    maxLines: Int = 1
) {
    Text(
        modifier = modifier,
        maxLines = maxLines,
        text = text,
        style = textStyle.copy(textAlign = textAlign, color = color)
    )
}

@Composable
fun TitleMediumText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = AppTheme.typography.title,
    textAlign: TextAlign = TextAlign.Start,
    maxLines: Int = 1,
    color: Color = AppTheme.colors.textColor,
    forceVerticalCenter: Boolean = false,
) {
    if (forceVerticalCenter) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Text(text = text, style = textStyle.copy(textAlign = textAlign, color = color), maxLines = maxLines)
        }
    } else {
        Text(modifier = modifier, text = text, style = textStyle.copy(textAlign = textAlign, color = color), maxLines = maxLines)
    }
}


@Composable
fun TitleMediumColoredText(
    modifier: Modifier = Modifier,
    texts: ImmutableList<String>,
    colors: ImmutableList<Color>,
    textStyle: TextStyle = AppTheme.typography.title,
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

