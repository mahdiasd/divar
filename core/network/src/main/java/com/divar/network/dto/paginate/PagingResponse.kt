package com.divar.network.dto.paginate

import kotlinx.serialization.Serializable

@Serializable
data class PagingResponse<T>(
    val content: T,
    val totalPage: Int,
    val totalElements: Long,
    val isFirst: Boolean,
    val isLast: Boolean,
)