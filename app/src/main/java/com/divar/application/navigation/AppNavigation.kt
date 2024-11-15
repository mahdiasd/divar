package com.divar.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.divar.ads.navigation.navigateToAds
import com.divar.ads_detail.navigation.adsDetailScreen
import com.divar.domain.model.category.Category
import com.divar.domain.model.filter.AdsFilter
import com.divar.filter.navigation.filterScreen
import com.divar.filter.navigation.navigateToFilter
import com.divar.location.navigation.locationScreen
import com.divar.location.navigation.navigateToLocation
import com.divar.main.navigation.mainScreen
import com.divar.main.navigation.navigateToMain
import com.divar.search.navigation.navigateToSearch
import com.divar.search.navigation.searchScreen
import com.divar.splash.navigation.splashRoute
import com.divar.splash.navigation.splashScreen
import com.divar.ui.extension.immutableListOf
import com.divar.ui.extension.runWithLifecycleAware
import com.divar.ui.model.FromScreen

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
            mainNavigation = {
                MainNavigation(
                    navController = mainNavController,
                    onSearch = {
                        rootNavController.runWithLifecycleAware {
                            navigateToSearch(it)
                        }
                    },
                    onCity = {},
                    onFilter = {
                        rootNavController.navigateToFilter(it)
                    }
                )
            },
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
        searchScreen(
            onSelected = {
                rootNavController.popBackStack()
                mainNavController.navigateToAds(it)
            },
            onBack = {
                rootNavController.popBackStack()
            }
        )

        filterScreen(
            onBack = {
                rootNavController.popBackStack()
            },
            onSaveFilter = {
                rootNavController.popBackStack()
                mainNavController.popBackStack()
                mainNavController.navigateToAds(it)
            }
        )

        adsDetailScreen(onBack = {
            rootNavController.runWithLifecycleAware {
                popBackStack()
            }
        })
    }

}