package com.divar.application.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.divar.category.navigation.categoryScreen
import com.divar.chat.navigation.chatScreen
import com.divar.home.navigation.homeRoute
import com.divar.home.navigation.homeScreen
import com.divar.main.navigation.mainRoute
import com.divar.profile.navigation.profileScreen

@Composable
fun MainNavigation(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        route = mainRoute,
        startDestination = homeRoute
    )
    {
        homeScreen()

        categoryScreen()

        chatScreen()

        profileScreen()
    }
}