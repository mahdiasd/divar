package com.divar.ads.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.divar.ads.AdsScreen
import com.divar.domain.model.category.CategoryOfAds
import com.divar.domain.model.filter.AdsFilter
import com.divar.ui.model.FilterClickType
import com.divar.utils.toJson
import java.net.URLEncoder

const val adsRoute = "ads_route/{filter}"

fun NavGraphBuilder.adsScreen(
    onCity: () -> Unit,
    onBack: () -> Unit,
    onSearch: (AdsFilter?) -> Unit,
    onFilter: (AdsFilter, FilterClickType) -> Unit
) {
    composable(
        route = adsRoute,
        arguments = listOf(navArgument("filter") { type = NavType.StringType })
    ) {
        AdsScreen(
            onBack = onBack,
            onSearch = onSearch,
            onCity = onCity,
            onFilter = onFilter
        )
    }
}

fun NavController.navigateToAds(adsFilter: AdsFilter) {
    val encoder = URLEncoder.encode(adsFilter.toJson()!! , "UTF-8")
    navigate(adsRoute.replace("{filter}", encoder))
}
