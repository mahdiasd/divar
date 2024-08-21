package com.divar.data.mapper

import com.divar.domain.model.Category
import com.divar.network.dto.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = icon,
        children = children.map { it.toDomain() }
    )
}