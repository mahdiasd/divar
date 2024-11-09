package com.divar.data.mapper.image

import com.divar.data.BuildConfig
import com.divar.domain.model.image.Image
import com.divar.network.dto.image.ImageResponse

fun ImageResponse.toDomain(): Image {
    return Image(path = BuildConfig.BaseUrl + "/" + path)
}