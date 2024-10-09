package com.divar.data.mapper.location

import com.divar.domain.model.location.City
import com.divar.network.dto.location.CityResponse

fun CityResponse.toDomain(): City {
    return City(id = id, name = name, neighborhoods = neighborhoods?.map { it.toDomain() })
}