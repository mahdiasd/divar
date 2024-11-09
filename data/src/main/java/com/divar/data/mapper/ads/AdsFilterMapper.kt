package com.divar.data.mapper.ads

import com.divar.data.mapper.parameter.toAnswerRequest
import com.divar.domain.model.filter.AdsFilter
import com.divar.network.dto.ads.GetAdsRequest

fun AdsFilter.toRequest(cityId: Long, page: Int): GetAdsRequest {
    val params = parameters?.mapNotNull { it.toAnswerRequest() }
    return GetAdsRequest(
        categoryId = category?.id,
        neighborhoodId = neighborhood?.id,
        cityId = cityId,
        price = price,
        parameters = if (params.isNullOrEmpty()) null else params,
        searchText = searchText,
        page = page
    )
}