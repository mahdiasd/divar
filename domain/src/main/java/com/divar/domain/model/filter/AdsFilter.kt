package com.divar.domain.model.filter

import androidx.compose.runtime.Immutable
import com.divar.domain.model.category.Category
import com.divar.domain.model.location.Neighborhood
import com.divar.domain.model.parameter.Parameter
import kotlinx.serialization.Serializable

@Serializable
@Immutable
data class AdsFilter(
    val category: Category? = null,
    val neighborhood: Neighborhood? = null,
    val price: String? = null,
    val parameters: List<Parameter>? = null,
    val searchText: String = "",
)
