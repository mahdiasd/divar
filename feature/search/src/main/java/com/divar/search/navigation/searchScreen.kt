package com.divar.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.domain.model.category.CategoryOfAds
import com.divar.search.SearchScreen

const val searchRoute = "search_route/{searchText}"
fun NavGraphBuilder.searchScreen(
    onSelected: (CategoryOfAds) -> Unit
) {
    composable(
        route = searchRoute,
        arguments = listOf(navArgument("searchText") { type = NavType.StringType })
    ) {

        SearchScreen(onSelected = onSelected)
    }
}

fun NavController.navigateToSearch(searchText: String) {
    navigate(searchRoute.replace("{searchText}", searchText))
}
