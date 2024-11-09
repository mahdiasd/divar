package com.divar.application.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.divar.ads.navigation.adsScreen
import com.divar.ads.navigation.navigateToAds
import com.divar.category.navigation.categoryScreen
import com.divar.chat.navigation.chatScreen
import com.divar.domain.model.filter.AdsFilter
import com.divar.home.navigation.homeRoute
import com.divar.home.navigation.homeScreen
import com.divar.main.navigation.mainRoute
import com.divar.profile.navigation.profileScreen
import com.divar.ui.extension.runWithLifecycleAware

@Composable
fun MainNavigation(
    navController: NavHostController,
    onSearch: (AdsFilter?) -> Unit,
    onCity: () -> Unit
) {
    NavHost(
        navController = navController,
        route = mainRoute,
        startDestination = homeRoute
    )
    {
        homeScreen(
            onCity = {},
            onSearch = { onSearch(null) },
            onSelectedCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(adsFilter = AdsFilter(category = it))
                }
            }
        )

        categoryScreen(
            onCategory = {
                navController.runWithLifecycleAware {
                    navigateToAds(adsFilter = AdsFilter(category = it))
                }
            }
        )

        chatScreen()

        profileScreen()

        adsScreen(
            onBack = { navController.popBackStack() },
            onSearch = onSearch,
            onCity = onCity,
            onFilter = { adsFilter, filterClickType ->

            }
        )
    }
}