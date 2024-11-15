package com.divar.filter.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.domain.model.filter.AdsFilter
import com.divar.filter.FilterScreen
import com.divar.ui.model.FromScreen
import com.divar.utils.toJson

const val filterRoute = "filter_route/{fromScreen}"
fun NavGraphBuilder.filterScreen(
    onBack: () -> Unit,
    onSaveFilter: (FromScreen) -> Unit,
) {
    composable(
        route = filterRoute,
        arguments = listOf(navArgument("fromScreen") { type = NavType.StringType })
    ) {
        FilterScreen(onBack = onBack, onSaveFilter = onSaveFilter)
    }
}

fun NavController.navigateToFilter(
    fromScreen: FromScreen
) {
    navigate(
        filterRoute.replace("{fromScreen}", fromScreen.toJson()!!)
    )
}
