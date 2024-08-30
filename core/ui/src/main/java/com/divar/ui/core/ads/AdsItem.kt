package com.divar.ui.core.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.ads.AdsSummary
import com.divar.ui.R
import com.divar.ui.core.text.BodyLargeText
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.toPrice
import com.divar.ui.them.AppTheme
import com.divar.utils.coilRounded


@Composable
fun AdsItem(
    modifier: Modifier = Modifier,
    adsSummary: AdsSummary,
    onClick: (AdsSummary) -> Unit
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
    ) {
        val columnRef = createRef()
        val imageRef = createRef()

        Column(
            modifier = Modifier
                .constrainAs(columnRef) {
                    start.linkTo(imageRef.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterVertically)
        ) {
            BodyLargeText(
                text = adsSummary.title,
                minLines = 2
            )

            BodyMediumText(text = stringResource(id = R.string.as_new))

            BodyMediumText(text = adsSummary.price.toPrice())

            BodyMediumText(text = adsSummary.createAt ?: "")

        }

        AsyncImage(
            modifier = Modifier
                .aspectRatio(1f)
                .constrainAs(imageRef) {
                    start.linkTo(parent.start)
                    top.linkTo(columnRef.top)
                    bottom.linkTo(columnRef.bottom)
                    height = Dimension.fillToConstraints
                },
            model = coilRounded(data = adsSummary.previewImage ?: AppTheme.colors.disableColor, radiusInDp = 2f),
            contentDescription = ""
        )
    }


}


@PreviewLightDark
@Composable
private fun Preview() {
    AppTheme {
        AdsItem(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppTheme.colors.backgroundColor),
            adsSummary = FakeData.provideAdsSummary().first()
        ) {

        }
    }
}