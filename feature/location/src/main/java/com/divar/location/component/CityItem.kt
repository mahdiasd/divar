package com.divar.location.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.divar.domain.fake_data.FakeData
import com.divar.domain.model.location.City
import com.divar.ui.core.text.BodyMediumText
import com.divar.ui.extension.animateClickable
import com.divar.ui.them.AppTheme
import retrofit2.http.Body

@Composable
fun CityItem(
    modifier: Modifier = Modifier,
    city: City,
    onClick: () -> Unit
) {
    BodyMediumText(
        modifier = Modifier.fillMaxWidth().animateClickable(onClick),
        text = city.name,
        textAlign = TextAlign.Start
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        CityItem(
            city = FakeData.provideCities().first()
        ) {

        }
    }
}