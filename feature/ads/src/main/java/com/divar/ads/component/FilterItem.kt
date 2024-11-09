package com.divar.ads.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.divar.ads.R
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme

@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    title: String,
    icon: Int? = null,
    isVisibleClose: Boolean = false,
    onClose: () -> Unit = {},
    isSelected: Boolean = isVisibleClose,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .animateClickable(onClick)
            .background(AppTheme.colors.itemColor, shape = AppTheme.shapes.roundLarge)
            .border(
                color = if (isSelected) AppTheme.colors.primaryColor else AppTheme.colors.hintColor,
                width = 1.dp,
                shape = AppTheme.shapes.roundLarge
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally)
    ) {
        if (isVisibleClose) {
            Icon(
                modifier = Modifier
                    .size(18.dp)
                    .animateClickable(onClose)
                    .background(AppTheme.colors.backgroundColor, shape = CircleShape)
                    .border(1.dp, color = AppTheme.colors.primaryColor, shape = CircleShape)
                    .padding(2.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "clear filter",
                tint = if (isSelected) AppTheme.colors.primaryColor else AppTheme.colors.iconColor
            )
        }

        LabelMediumText(
            modifier = Modifier.padding(top = 4.dp),
            text = title,
            color = if (isSelected) AppTheme.colors.primaryColor else AppTheme.colors.titleColor
        )

        icon?.let {
            Icon(
                modifier = Modifier
                    .size(16.dp)
                    .animateClickable(onClose),
                painter = painterResource(id = it),
                contentDescription = "",
                tint = if (isSelected) AppTheme.colors.primaryColor else AppTheme.colors.iconColor
            )
        }

    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        FilterItem(
            title = "دسته بندی",
            icon = com.divar.ui.R.drawable.ic_category
        ) {

        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewSelected() {
    AppTheme {
        FilterItem(
            title = "دسته بندی",
            isVisibleClose = true,
            icon = com.divar.ui.R.drawable.ic_category
        ) {

        }
    }
}