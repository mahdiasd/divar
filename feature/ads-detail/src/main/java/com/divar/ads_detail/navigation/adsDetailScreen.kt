package com.divar.ads_detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.ads_detail.AdsDetailScreen

const val adsDetailRoute = "adsDetail_route/{id}"
fun NavGraphBuilder.adsDetailScreen(
    onBack: () -> Unit
) {
    composable(
        route = adsDetailRoute,
        arguments = listOf(navArgument("id") { type = NavType.IntType })
    ) {
        AdsDetailScreen(onBack = onBack)
    }
}

fun NavController.navigateToAdsDetail(id: Long) {
    navigate(adsDetailRoute.replace("{id}", id.toString()))
}
