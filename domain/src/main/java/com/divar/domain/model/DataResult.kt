package com.divar.domain.model

import kotlinx.serialization.Serializable


@Serializable
sealed class DataResult<T> {
    @Serializable
    data class Success<T>(val data: T, val message: String = "") : DataResult<T>()

    @Serializable
    data class Failure<T>(val apiError: ApiError) : DataResult<T>()
}

suspend fun <T> DataResult<T>.onSuccess(
    executable: suspend (T) -> Unit
): DataResult<T> = apply {
    if (this is DataResult.Success)
        executable(this.data)
}

suspend fun <T> DataResult<T>.onFailure(
    executable: suspend (ApiError) -> Unit
): DataResult<T> = apply {
    if (this is DataResult.Failure)
        executable(this.apiError)
}