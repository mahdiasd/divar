package com.divar.ui.core.filter_item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.divar.ui.R
import com.divar.ui.core.input.AppTextField
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String? = null,
    onClick: () -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .animateClickable(onClick)
                .weight(1f),
            maxLines = 1,
            textAlign = TextAlign.End,
            color = AppTheme.colors.hintColor,
            text = value ?: stringResource(id = R.string.select)
        )

        BodyMediumText(
            text = title
        )

    }

}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    title: String,
    value: Boolean = false,
    onClick: () -> Unit
) {

    Row(
        Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Checkbox(checked = value, onCheckedChange = { onClick() })
        BodyMediumText(
            text = title
        )
    }

}

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    onChangeText: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically)
    ) {
        BodyMediumText(
            text = title
        )

        AppTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onChangeText,
            hint = ""
        )
    }


}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
        ) {
            FilterItem(title = "مایل به معاوضه", value = true, onClick = {})
            FilterItem(title = "دسته بندی", value = null, onClick = {})
            FilterItem(title = "متراژ", value = "", onChangeText = {})
        }
    }
}
