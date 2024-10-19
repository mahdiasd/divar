package com.divar.main.fake_data

import com.divar.main.model.BottomBarItem
import com.divar.ui.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

object MainFakeData {
    fun provideBottomBars(): ImmutableList<BottomBarItem> {
        return listOf(
            BottomBarItem(R.string.ads, R.drawable.ic_home, ""),
            BottomBarItem(R.string.category, R.drawable.ic_category, ""),
            BottomBarItem(R.string.create_ads, R.drawable.ic_plus, ""),
            BottomBarItem(R.string.chat, R.drawable.ic_chat, ""),
            BottomBarItem(R.string.my_divar, R.drawable.ic_user, "")
        ).toImmutableList()
    }
}