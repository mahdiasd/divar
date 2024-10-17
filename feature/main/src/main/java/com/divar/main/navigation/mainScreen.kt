package com.divar.main.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.main.MainScreen

const val mainRoute = "main_route"
fun NavGraphBuilder.mainScreen(
) {
    composable(
        route = mainRoute,
    ) {
        MainScreen()
    }
}

fun NavController.navigateToMain() {
    navigate(mainRoute)
}
