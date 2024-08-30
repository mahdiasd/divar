package com.divar.network.dto.ads

import com.divar.network.dto.image.ImageResponse
import com.divar.network.dto.neighborhood.NeighborhoodResponse
import kotlinx.serialization.Serializable

@Serializable
data class AdsSummaryResponse(
    val id: Long,
    val title: String,
    val price: String,
    val neighborhood: NeighborhoodResponse,
    val previewImage: ImageResponse?,
    val createAt: String? = null,
)
