package com.divar.domain.model.location

import kotlinx.serialization.Serializable

@Serializable
enum class LocationScreenType {
    FromLogin,
    FromCreateAds
}