package com.divar.network.dto.ads

import com.divar.network.dto.category.CategoryResponse
import com.divar.network.dto.image.ImageResponse
import com.divar.network.dto.neighborhood.NeighborhoodResponse
import com.divar.network.dto.parameter.ParameterAnswerResponse
import com.divar.network.dto.user.UserResponse
import java.time.Instant

data class AdsResponse(
    val id: Long,

    val title: String,

    val description: String,

    val price: String,

    val neighborhood: NeighborhoodResponse,

    val user: UserResponse,

    val category: CategoryResponse,

    val images: List<ImageResponse>,

    val answers: List<ParameterAnswerResponse>,

    val createAt: Instant? = null,

    val updatedAt: Instant? = null,
)