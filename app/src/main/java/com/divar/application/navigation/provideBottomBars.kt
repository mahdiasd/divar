package com.divar.application.navigation

import com.divar.category.navigation.categoryRoute
import com.divar.chat.navigation.chatRoute
import com.divar.home.navigation.homeRoute
import com.divar.main.model.BottomBarItem
import com.divar.profile.navigation.profileRoute
import com.divar.ui.R

fun provideBottomBars(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(R.string.my_divar, R.drawable.ic_user, profileRoute),
        BottomBarItem(R.string.chat, R.drawable.ic_chat, chatRoute),
        BottomBarItem(R.string.create_ads, R.drawable.ic_plus, ""),
        BottomBarItem(R.string.category, R.drawable.ic_category, categoryRoute),
        BottomBarItem(R.string.ads, R.drawable.ic_home, homeRoute)
    )
}