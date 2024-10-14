package com.divar.splash.navigation

import androidx.core.splashscreen.SplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.splash.SplashScreen

const val splashRoute = "splash_route"

fun NavGraphBuilder.splashScreen(
) {
    composable(
        route = splashRoute,
    ) {
        SplashScreen()
    }
}

fun NavController.navigateToSplash() {
    navigate(splashRoute)
}
