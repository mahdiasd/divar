package com.divar.domain.model.ads

import com.divar.domain.model.category.Category
import com.divar.domain.model.location.Neighborhood
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

data class CreateAdsParam(
    val category: Category? = null,
    val neighborhood: Neighborhood? = null,
    val images: ImmutableList<String> = listOf("", "", "", "", "", "").toImmutableList(),
    val title: String = "",
    val description: String = "",
    val price: String = "",
)
