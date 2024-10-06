package com.divar.data.mapper.neighborhood

import com.divar.domain.model.neighborhood.Neighborhood
import com.divar.network.dto.neighborhood.NeighborhoodResponse

fun NeighborhoodResponse.toDomain(): Neighborhood {
    return Neighborhood(id = id, name = name)
}