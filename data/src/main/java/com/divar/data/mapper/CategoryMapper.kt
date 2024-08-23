package com.divar.data.mapper

import com.divar.domain.model.Category
import com.divar.network.dto.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = "http://192.168.69.157:8080/$icon",
        children = children.map { it.toDomain() }
    )
}