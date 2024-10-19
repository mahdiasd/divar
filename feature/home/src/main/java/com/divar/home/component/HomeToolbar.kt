package com.divar.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.divar.home.R
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelSmallText
import com.divar.ui.extension.baseModifier
import com.divar.ui.them.AppTheme

@Composable
fun HomeToolbar(
    modifier: Modifier = Modifier,
    cityName: String,
    onCity: () -> Unit,
    onSearch: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.itemColor)
            .padding(8.dp)
            .border(1.dp, AppTheme.colors.hintColor, shape = AppTheme.shapes.roundExtraSmall)
            .padding(4.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.LocationOn,
            contentDescription = "location icon",
            tint = AppTheme.colors.hintColor
        )

        LabelSmallText(
            modifier = Modifier.padding(top = 4.dp),
            text = "مشهد"
        )

        VerticalDivider(
            modifier = Modifier
                .background(AppTheme.colors.hintColor)
                .padding(start = 0.dp)
                .height(20.dp)
        )
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Outlined.Search,
            contentDescription = "search icon",
            tint = AppTheme.colors.hintColor
        )

        BodyMediumText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 4.dp),
            text = stringResource(id = com.divar.ui.R.string.search_on_all_ads),
            color = AppTheme.colors.hintColor
        )
    }
}

@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        Box(modifier = Modifier.baseModifier())
        {
            HomeToolbar(
                modifier = Modifier,
                cityName = "",
                onCity = {},
                onSearch = {}
            )
        }
    }
}