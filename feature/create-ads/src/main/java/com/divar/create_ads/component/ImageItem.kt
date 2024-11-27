package com.divar.create_ads.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.divar.ui.R
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme

@Composable
fun ImageItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.Image,
    title: Int = R.string.image,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateClickable(onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            imageVector = imageVector,
            contentDescription = "",
            colorFilter = ColorFilter.tint(AppTheme.colors.hintColor)
        )
        LabelMediumText(text = stringResource(id = title))
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        ImageItem(onClick = {})
    }
}