package com.divar.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.domain.model.category.Category
import com.divar.home.HomeScreen

const val homeRoute = "home_route"
fun NavGraphBuilder.homeScreen(
    onCity: () -> Unit,
    onSearch: () -> Unit,
    onSelectedCategory: (Category) -> Unit
) {
    composable(
        route = homeRoute,
    ) {
        HomeScreen(
            onCity = onCity,
            onSearch = onSearch,
            onSelectedCategory = onSelectedCategory
        )
    }
}

fun NavController.navigateToHome() {
    navigate(homeRoute)
}
