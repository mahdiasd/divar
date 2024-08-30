package com.divar.data.mapper.category

import com.divar.domain.model.category.Category
import com.divar.network.dto.category.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = "http://192.168.69.157:8080/$icon",
        children = children.map { it.toDomain() }
    )
}