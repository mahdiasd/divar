package com.divar.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.auth.AuthScreen

const val authRoute = "auth_route"
fun NavGraphBuilder.authScreen(
) {
    composable(
        route = authRoute,
    ) {
        AuthScreen()
    }
}

fun NavController.navigateToAuth() {
    navigate(authRoute)
}
