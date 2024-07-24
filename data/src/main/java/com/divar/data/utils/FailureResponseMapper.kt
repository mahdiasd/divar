package com.divar.data.utils

import com.divar.domain.model.ApiError
import com.divar.domain.model.BadRequestError
import com.divar.domain.model.ConflictError
import com.divar.domain.model.ForbiddenError
import com.divar.domain.model.GoneError
import com.divar.domain.model.NotFoundError
import com.divar.domain.model.ServerError
import com.divar.domain.model.ServiceUnavailableError
import com.divar.domain.model.TooManyRequestsError
import com.divar.domain.model.UnauthorizedError
import com.divar.domain.model.UnsupportedMediaTypeError
import com.divar.network.model.FailureResponse

fun FailureResponse.toApiError(): ApiError {
    return when (this.errorCode) {
        404 -> NotFoundError(httpStatus = this.errorCode, message = this.message)
        401 -> UnauthorizedError(httpStatus = this.errorCode, message = this.message)
        403 -> ForbiddenError(httpStatus = this.errorCode, message = this.message)
        400 -> BadRequestError(httpStatus = this.errorCode, message = this.message)
        409 -> ConflictError(httpStatus = this.errorCode, message = this.message)
        410 -> GoneError(httpStatus = this.errorCode, message = this.message)
        415 -> UnsupportedMediaTypeError(httpStatus = this.errorCode, message = this.message)
        429 -> TooManyRequestsError(httpStatus = this.errorCode, message = this.message)
        503 -> ServiceUnavailableError(httpStatus = this.errorCode, message = this.message)
        500 -> ServerError(httpStatus = this.errorCode, message = this.message)
        else -> object : ApiError {
            override val httpStatus: Int = this@toApiError.errorCode
            override val message: String = this@toApiError.message.ifEmpty { "An unknown error occurred." }
        }
    }
}
