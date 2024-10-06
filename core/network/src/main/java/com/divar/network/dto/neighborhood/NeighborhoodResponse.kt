package com.divar.network.dto.neighborhood

import kotlinx.serialization.Serializable

@Serializable
data class NeighborhoodResponse(
    val id: Long,
    val name: String,
)
