package com.divar.data.mapper.ads

import com.divar.data.mapper.category.toDomain
import com.divar.data.mapper.image.toDomain
import com.divar.data.mapper.neighborhood.toDomain
import com.divar.data.mapper.parameter.toDomain
import com.divar.data.mapper.user.toDomain
import com.divar.domain.model.ads.Ads
import com.divar.domain.model.ads.AdsSummary
import com.divar.network.dto.ads.AdsResponse
import com.divar.network.dto.ads.AdsSummaryResponse

fun AdsSummaryResponse.toDomain(): AdsSummary {
    return AdsSummary(
        id = id,
        title = title,
        price = price,
        neighborhood = neighborhood.toDomain(),
        previewImage = previewImage?.toDomain(),
        createAt = createAt
    )
}

fun AdsResponse.toDomain(): Ads {
    return Ads(
        id = id,
        title = title,
        description = description,
        price = price,
        neighborhood = neighborhood.toDomain(),
        user = user.toDomain(),
        category = category.toDomain(),
        images = images.map { it.toDomain() },
        answers = answers.map { it.toDomain() },
        createAt = createAt,
        updatedAt = updatedAt
    )
}