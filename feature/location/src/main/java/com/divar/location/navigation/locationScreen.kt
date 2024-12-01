package com.divar.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.domain.model.location.LocationScreenType
import com.divar.location.LocationScreen

const val locationRoute = "location_route/{screenType}"

fun NavGraphBuilder.locationScreen(
    onMoveToMain: () -> Unit,
    onBack: () -> Unit,
) {
    composable(
        route = locationRoute,
        arguments = listOf(navArgument("screenType") { type = NavType.StringType })
    ) {
        LocationScreen(
            onMoveToMain = onMoveToMain,
            onBack = onBack
        )
    }
}

fun NavController.navigateToLocation(locationScreenType: LocationScreenType) {
    navigate(locationRoute.replace("{screenType}", locationScreenType.name))
}
