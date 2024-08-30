package com.divar.domain.model.paginate

data class Paging<T>(
    val content: T,
    val totalPage: Int,
    val totalElements: Long,
    val isFirst: Boolean,
    val isLast: Boolean,
)