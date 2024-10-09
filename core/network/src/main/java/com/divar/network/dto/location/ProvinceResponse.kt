package com.divar.network.dto.location

import kotlinx.serialization.Serializable

@Serializable
data class ProvinceResponse(
    val id: Long,
    val name: String,
    val cities: List<CityResponse>? = null
)