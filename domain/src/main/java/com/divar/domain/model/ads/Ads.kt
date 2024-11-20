package com.divar.domain.model.ads

import com.divar.domain.model.category.Category
import com.divar.domain.model.image.Image
import com.divar.domain.model.location.Neighborhood
import com.divar.domain.model.parameter.ParameterAnswer
import com.divar.domain.model.user.User
import java.time.Instant

data class Ads(
    val id: Long,

    val title: String,

    val description: String,

    val price: String,

    val neighborhood: Neighborhood,

    val user: User,

    val category: Category,

    val images: List<Image>,

    val answers: List<ParameterAnswer>,

    val createAt: String? = null,

    val updatedAt: String? = null,
)
