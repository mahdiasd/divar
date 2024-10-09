package com.divar.domain.model.location

import kotlinx.serialization.Serializable

@Serializable
data class Neighborhood(
    val id: Long,
    val name: String,
)
