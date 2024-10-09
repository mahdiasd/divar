package com.divar.domain.model.ads

import com.divar.domain.model.image.Image
import com.divar.domain.model.location.Neighborhood

data class AdsSummary(
    val id: Long,
    val title: String,
    val price: String,
    val neighborhood: Neighborhood,
    val previewImage: Image?,
    val createAt: String? = null,
)