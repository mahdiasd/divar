package com.divar.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.domain.model.category.CategoryOfAds
import com.divar.search.SearchScreen
import com.divar.ui.model.FromScreen
import com.divar.utils.toJson

const val searchRoute = "search_route/{fromScreen}"
fun NavGraphBuilder.searchScreen(
    onSelected: (FromScreen) -> Unit,
    onBack: () -> Unit
) {
    composable(
        route = searchRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {

        SearchScreen(onSelected = onSelected, onBack = onBack)
    }
}

fun NavController.navigateToSearch(fromScreen: FromScreen) {
    navigate(searchRoute.replace("{fromScreen}", fromScreen.toJson()!!))
}
