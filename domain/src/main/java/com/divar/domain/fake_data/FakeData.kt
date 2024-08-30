package com.divar.domain.fake_data

import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.category.Category
import com.divar.domain.model.neighborhood.Neighborhood
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
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),   Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),   Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),   Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),   Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ), Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
            Category(
                name = "خودرو",
                id = 3122,
                icon = "",
                children = listOf()
            ),
        ).toImmutableList()
    }

    fun provideAdsSummary(): List<AdsSummary> {
        return listOf(
            AdsSummary(
                id = 71216,
                title = "تانکر آبرسانی و آبرسانی حمل آب شرب و غیر شرب",
                price = "1000000",
                neighborhood = Neighborhood(id = 73, name = "بلوار کشاورز"),
                previewImage = null,
                createAt = "2023-12-30T11:37:56Z"
            ),
            AdsSummary(
                id = 63871,
                title = "Them because site protect job part the.",
                price = "672645",
                neighborhood = Neighborhood(id = 53, name = "مرند قدیم"),
                previewImage = null,
                createAt = "2023-08-25T10:27:52Z"
            ),
            AdsSummary(
                id = 62160,
                title = "Theory what music.",
                price = "980461",
                neighborhood = Neighborhood(id = 23, name = "قمصر"),
                previewImage = null,
                createAt = "2024-05-16T15:38:38Z"
            ),
            AdsSummary(
                id = 55986,
                title = "Response shoulder think across.",
                price = "761244",
                neighborhood = Neighborhood(id = 88, name = "محمدان"),
                previewImage = null,
                createAt = "2024-05-26T04:26:29Z"
            ),
            AdsSummary(
                id = 53323,
                title = "Size draw animal hit short.",
                price = "58611",
                neighborhood = Neighborhood(id = 15, name = "ولیعصر"),
                previewImage = null,
                createAt = "2024-07-22T18:16:26Z"
            )
        )
    }

}