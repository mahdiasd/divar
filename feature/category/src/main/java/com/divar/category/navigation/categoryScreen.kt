package com.divar.category.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.divar.category.CategoryScreen

const val categoryRoute = "category_route"
fun NavGraphBuilder.categoryScreen(
) {
    composable(
        route = categoryRoute,
    ) {
        CategoryScreen()
    }
}

fun NavController.navigateToCategory() {
    navigate(categoryRoute)
}
