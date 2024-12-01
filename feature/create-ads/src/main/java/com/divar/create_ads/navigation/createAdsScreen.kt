package com.divar.create_ads.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.create_ads.CreateAdsScreen

const val createAdsRoute = "create_ads_route"
fun NavGraphBuilder.createAdsScreen(
    onBack: () -> Unit,
    onLocation: () -> Unit,
) {
    composable(
        route = createAdsRoute,
    ) {
        CreateAdsScreen(onBack = onBack, onLocation = onLocation)
    }
}

fun NavController.navigateToCreateAds() {
    navigate(createAdsRoute)
}
