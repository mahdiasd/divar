package com.divar.data.mapper.location

import com.divar.domain.model.location.Neighborhood
import com.divar.network.dto.neighborhood.NeighborhoodResponse

fun NeighborhoodResponse.toDomain(): Neighborhood {
    return Neighborhood(id = id, name = name)
}