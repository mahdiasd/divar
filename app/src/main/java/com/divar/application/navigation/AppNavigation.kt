package com.divar.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.divar.location.navigation.locationScreen
import com.divar.location.navigation.navigateToLocation
import com.divar.main.navigation.mainScreen
import com.divar.main.navigation.navigateToMain
import com.divar.splash.navigation.splashRoute
import com.divar.splash.navigation.splashScreen
import com.divar.ui.extension.runWithLifecycleAware

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = splashRoute
    )
    {
        splashScreen(
            onMoveToMain = {
                navController.runWithLifecycleAware { navController.navigateToMain() }
            },
            onMoveToLocation = {
                navController.runWithLifecycleAware { navController.navigateToLocation() }
            }
        )

        mainScreen()

        locationScreen(
            onMoveToMain = {
                navController.runWithLifecycleAware { navController.navigateToMain() }
            }
        )
    }

}