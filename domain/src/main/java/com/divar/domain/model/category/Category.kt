package com.divar.domain.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val name: String,
    val id: Long,
    val icon: String,
    val children: List<Category>
)