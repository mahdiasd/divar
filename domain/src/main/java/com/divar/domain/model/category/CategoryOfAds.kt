package com.divar.domain.model.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryOfAds(
    val categoryName: String,
    val categoryId: Long,
    val adsCount: Long,
    val adsTitle: String
)
