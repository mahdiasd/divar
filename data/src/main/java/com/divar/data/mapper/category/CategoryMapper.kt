package com.divar.data.mapper.category

import com.divar.domain.BuildConfig
import com.divar.domain.model.category.Category
import com.divar.network.dto.category.CategoryResponse

fun CategoryResponse.toDomain(): Category {
    return Category(
        id = id,
        name = name,
        icon = "${BuildConfig.BaseUrl}/$icon",
        children = children.map { it.toDomain() }
    )
}