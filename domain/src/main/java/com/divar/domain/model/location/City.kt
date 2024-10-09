package com.divar.domain.model.location

import kotlinx.serialization.Serializable


@Serializable
data class City(
    val id: Long = 0,
    val name: String,
    val neighborhoods: List<Neighborhood>? = null
)
