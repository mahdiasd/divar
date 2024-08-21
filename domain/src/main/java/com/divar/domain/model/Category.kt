package com.divar.domain.model

data class Category(
    val name: String,
    val id: Long,
    val icon: String,
    val children: List<Category>
)