package com.divar.domain.model.ads

import com.divar.domain.model.category.Category

data class CreateAdsParam(
    val category: Category? = null,
    val images: List<String> = listOf("", "", "", "", "", ""),
    val title: String = "",
    val description: String = "",
)
