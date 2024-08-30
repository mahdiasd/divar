package com.divar.data.mapper.ads

import com.divar.data.mapper.image.toDomain
import com.divar.data.mapper.neighborhood.toDomain
import com.divar.domain.model.ads.AdsSummary
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