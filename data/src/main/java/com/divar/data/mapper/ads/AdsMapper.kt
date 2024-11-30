package com.divar.data.mapper.ads

import com.divar.data.mapper.category.toDomain
import com.divar.data.mapper.image.toDomain
import com.divar.data.mapper.neighborhood.toDomain
import com.divar.data.mapper.parameter.toDomain
import com.divar.data.mapper.user.toDomain
import com.divar.domain.model.ads.Ads
import com.divar.domain.model.ads.AdsSummary
import com.divar.domain.model.ads.CreateAdsParam
import com.divar.network.dto.ads.AdsResponse
import com.divar.network.dto.ads.AdsSummaryResponse
import com.divar.network.dto.ads.CreateAdsRequest
import com.divar.network.dto.ads.ParameterAnswerRequest

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

fun CreateAdsParam.toRequest(): CreateAdsRequest {
    return CreateAdsRequest(
        id = null,
        title = title,
        description = description,
        price = price,
        neighborhoodId = 1,
        categoryId = category!!.id,
        answers = parameters.map {
            ParameterAnswerRequest(
                answer = it.answer.toString(),
                parameterId = it.id
            )
        }
    )
}