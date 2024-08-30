package com.divar.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.category.Category
import com.divar.ui.core.text.LabelSmallText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme
import com.divar.utils.svgCoil

@Composable
fun CategoryHomeItem(
    modifier: Modifier = Modifier,
    category: Category,
    onClick: (Category) -> Unit
) {

    Column(
        modifier = Modifier
            .then(modifier)
            .animateClickable { onClick(category) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        category.icon.takeIf { it.isNotBlank() }?.let { icon ->
            AsyncImage(
                modifier = Modifier.size(20.dp),
                model = svgCoil(icon),
                contentDescription = "category icon",
                colorFilter = ColorFilter.tint(color = AppTheme.colors.iconColor),
            )
        }
        LabelSmallText(text = category.name)
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        CategoryHomeItem(
            modifier = Modifier
                .background(AppTheme.colors.backgroundColor),
            category = FakeData.provideCategories().first(),
            onClick = {}
        )
    }
}