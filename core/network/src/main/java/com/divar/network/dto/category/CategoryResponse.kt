package com.divar.network.dto.category

import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponse(
    val name: String,
    val id: Long,
    val icon: String,
    val children: List<CategoryResponse>
)
