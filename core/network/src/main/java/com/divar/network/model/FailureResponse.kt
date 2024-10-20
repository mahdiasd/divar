package com.divar.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FailureResponse(
    val status: Status = Status.Failure,
    val message: String = "",
    val errorCode: Int
)
