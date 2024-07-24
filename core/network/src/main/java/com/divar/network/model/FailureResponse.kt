package com.divar.network.model

data class FailureResponse(
    val status: Status = Status.Failure,
    val message: String = "",
    val errorCode: Int
)
