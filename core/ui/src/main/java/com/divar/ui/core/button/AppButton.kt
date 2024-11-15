package com.divar.ui.core.button

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.divar.ui.R
import com.divar.ui.core.text.TitleMediumText
import com.divar.ui.them.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primaryColor,
        ),
        shape = AppTheme.shapes.roundSmall
    ) {
        TitleMediumText(
            text = stringResource(id = text),
            color = Color.White
        )
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = R.string.save_filters
        ) {

        }
    }
}