package com.divar.data.mapper.category

import com.divar.domain.model.category.CategoryOfAds
import com.divar.network.dto.category.CategoryOfAdsResponse

fun CategoryOfAdsResponse.toDomain(): CategoryOfAds {
    return CategoryOfAds(
        categoryName = categoryName,
        categoryId = categoryId,
        adsCount = adsCount,
        adsTitle = adsTitle
    )
}