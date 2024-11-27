package com.divar.create_ads.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.divar.create_ads.CreateAdsUiEvent
import com.divar.create_ads.OnAction
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.ui.R
import com.divar.ui.core.filter_item.FilterItem
import com.divar.ui.core.text.BodyLargeText
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.them.AppTheme

@Composable
fun Step1Content(createAdsParam: CreateAdsParam, onAction: OnAction) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        FilterItem(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(id = R.string.category),
            value = createAdsParam.category?.name,
            onClick = { onAction(CreateAdsUiEvent.DismissDialog) }
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            thickness = 0.5.dp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = ""
            )
            BodyMediumText(text = stringResource(id = R.string.create_ads_guide))
        }

        HorizontalDivider(
            thickness = 0.5.dp,
            color = AppTheme.colors.hintColor
        )

        BodyLargeText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.image_of_ads)
        )

        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.add_image_point),
            color = AppTheme.colors.hintColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp),
            rows = GridCells.Adaptive(minSize = 100.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(createAdsParam.images.size)
            { index ->
                ImageItem(
                    path = createAdsParam.images[index],
                    onClick = {}
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        Step1Content(createAdsParam = CreateAdsParam()) {

        }
    }
}
