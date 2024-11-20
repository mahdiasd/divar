package com.divar.data.mapper.user

import com.divar.domain.model.user.User
import com.divar.network.dto.user.UserResponse

fun UserResponse.toDomain(): User {
    return User(
        name = name,
        family = family,
        email = email,
        token = token,
        mobile = mobile,
        createAt = createAt,
        updatedAt = updatedAt
    )
}