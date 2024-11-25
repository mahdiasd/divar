package com.divar.network.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val mobile: String,
    val password: String,
    val repeatPassword: String? = null,
)
