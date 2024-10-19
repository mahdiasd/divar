package com.divar.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.divar.location.navigation.locationScreen
import com.divar.location.navigation.navigateToLocation
import com.divar.main.navigation.mainRoute
import com.divar.main.navigation.mainScreen
import com.divar.main.navigation.navigateToMain
import com.divar.splash.navigation.splashRoute
import com.divar.splash.navigation.splashScreen
import com.divar.ui.extension.runWithLifecycleAware

@Composable
fun AppNavigation() {

    val rootNavController = rememberNavController()
    val mainNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = splashRoute
    )
    {
        splashScreen(
            onMoveToMain = {
                rootNavController.runWithLifecycleAware { rootNavController.navigateToMain() }
            },
            onMoveToLocation = {
                rootNavController.runWithLifecycleAware { rootNavController.navigateToLocation() }
            }
        )

        mainScreen(
            bottomBarItems = provideBottomBars(),
            mainNavigation = { MainNavigation(navController = mainNavController) },
            onChangeBottomBar = {
                it.route.takeIf { bottomBarItem -> bottomBarItem.isNotEmpty() }?.let { route ->
                    mainNavController.runWithLifecycleAware {
                        navigate(route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(mainNavController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            // Avoid multiple copies of the same destination when
                            // re-selecting the same item
                            launchSingleTop = true

                            // Restore state when re-selecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            }
        )

        locationScreen(
            onMoveToMain = {
                rootNavController.runWithLifecycleAware { rootNavController.navigateToMain() }
            }
        )
    }

}