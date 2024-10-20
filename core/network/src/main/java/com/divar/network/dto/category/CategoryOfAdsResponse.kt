package com.divar.network.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryOfAdsResponse(
    val categoryName: String,
    val categoryId: Long,
    val adsCount: Long,
    val adsTitle: String
)
