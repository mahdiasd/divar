package com.divar.network.dto.ads

import kotlinx.serialization.Serializable

@Serializable
data class GetAdsRequest(
    val categoryId: Long?,
    val neighborhoodId: Long?,
    val cityId: Long,
    val price: String?,
    val parameters: List<ParameterAnswerRequest>?,
    val searchText: String = "",
    val page: Int
)


