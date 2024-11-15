package com.divar.network.dto.user

import java.time.Instant

data class UserResponse(
    val name: String,

    val family: String,

    val email: String,

    val token: String,

    val mobile: String,

    val createAt: Instant?,

    val updatedAt: Instant?,
)
