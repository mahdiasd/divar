package com.divar.create_ads.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.divar.create_ads.CreateAdsUiEvent
import com.divar.create_ads.OnAction
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.domain.model.filter.FilterClickType
import com.divar.ui.R
import com.divar.ui.core.filter_item.FilterItem
import com.divar.ui.core.text.BodyLargeText
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.core.text.LabelMediumText
import com.divar.ui.extension.animateClickable
import retrofit2.http.Body

@Composable
fun Step1Content(createAdsParam: CreateAdsParam, onAction: OnAction) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
    ) {
        FilterItem(
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

        BodyLargeText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.image_of_ads)
        )

        BodyMediumText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = stringResource(id = R.string.add_image_point)
        )
    }
}

