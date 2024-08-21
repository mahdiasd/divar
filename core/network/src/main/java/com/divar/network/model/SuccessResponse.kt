package com.divar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse<T>(
    val status: Status = Status.Success,
    val data: T?,
    val message: String = ""
)
