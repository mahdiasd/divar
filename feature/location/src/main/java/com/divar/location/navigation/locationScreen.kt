package com.divar.location.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.location.LocationScreen

const val locationRoute = "location_route"

fun NavGraphBuilder.locationScreen(
    onMoveToMain : () -> Unit
) {
    composable(
        route = locationRoute,
    ) {
        LocationScreen(
            onMoveToMain = onMoveToMain
        )
    }
}

fun NavController.navigateToLocation() {
    navigate(locationRoute)
}
