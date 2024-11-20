package com.divar.network.dto.parameter

import kotlinx.serialization.Serializable

@Serializable
data class ParameterAnswerResponse(
    val answer: String,
    val parameter: ParameterResponse,
)
