package com.divar.create_ads.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.create_ads.CreateAdsScreen

const val createAdsRoute = "create_ads_route"
fun NavGraphBuilder.createAdsScreen(
    onBack: () -> Unit,
) {
    composable(
        route = createAdsRoute,
    ) {
        CreateAdsScreen(onBack = onBack)
    }
}

fun NavController.navigateToCreateAds() {
    navigate(createAdsRoute)
}
