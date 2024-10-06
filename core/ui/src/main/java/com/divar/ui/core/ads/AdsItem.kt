package com.divar.ui.core.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
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
import com.divar.ui.extension.animateClickable
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
            .animateClickable { onClick(adsSummary) }
            .then(modifier)
    ) {
        val columnRef = createRef()
        val imageRef = createRef()

        Column(
            modifier = Modifier
                .constrainAs(columnRef) {
                    top.linkTo(parent.top)
                    start.linkTo(imageRef.end , 8.dp)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(2.dp, alignment = Alignment.CenterVertically)
        ) {
            BodyLargeText(
                text = adsSummary.title,
                minLines = 2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            BodyMediumText(
                text = stringResource(id = R.string.as_new),
                color = AppTheme.colors.hintColor
            )

            BodyMediumText(
                text = adsSummary.price.toPrice(),
                color = AppTheme.colors.hintColor
            )

            BodyMediumText(
                text = adsSummary.createAt ?: "",
                color = AppTheme.colors.hintColor
            )
        }

        adsSummary.previewImage?.path?.let {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.Gray, shape = AppTheme.shapes.roundSmall)
                    .clip(AppTheme.shapes.roundSmall)
                    .constrainAs(imageRef) {
                        start.linkTo(parent.start)
                        top.linkTo(columnRef.top)
                        bottom.linkTo(columnRef.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.preferredWrapContent
                    },
                contentScale = ContentScale.Crop,
                model = coilRounded(data = it, radiusInDp = 0f),
                contentDescription = ""
            )
        } ?: run {
            Icon(
                modifier = Modifier
                    .aspectRatio(1f)
                    .background(Color.Gray, shape = AppTheme.shapes.roundSmall)
                    .padding(42.dp)
                    .clip(AppTheme.shapes.roundSmall)
                    .constrainAs(imageRef) {
                        start.linkTo(parent.start)
                        top.linkTo(columnRef.top)
                        bottom.linkTo(columnRef.bottom)
                        height = Dimension.fillToConstraints
                        width = Dimension.preferredWrapContent
                    },
                painter = painterResource(id = R.drawable.ic_no_camera),
                contentDescription = "ads_with_no_image",
                tint = AppTheme.colors.hintColor
            )
        }
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