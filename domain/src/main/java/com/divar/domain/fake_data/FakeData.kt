package com.divar.domain.fake_data

import com.divar.domain.model.Category
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

object FakeData {

    fun provideCategories(): ImmutableList<Category> {
        return listOf(
            Category(
                name = "املاک",
                id = 3122,
                icon = "",
                children = listOf(
                    Category(
                        name = "آپارتمان",
                        id = 9604,
                        icon = "civibus",
                        children = listOf()
                    )
                )
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            )
        ).toImmutableList()
    }
}