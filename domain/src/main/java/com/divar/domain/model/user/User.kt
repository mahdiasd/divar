package com.divar.domain.model.user

import java.time.Instant

data class User(
    val name: String,

    val family: String,

    val email: String,

    val token: String,

    val mobile: String,

    val createAt: Instant?,

    val updatedAt: Instant?,
)
