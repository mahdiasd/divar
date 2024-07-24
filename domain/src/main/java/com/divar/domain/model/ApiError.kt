package com.divar.domain.model

interface ApiError {
    val httpStatus: Int
    val message: String
}

data class NotFoundError(
    override val httpStatus: Int,
    override val message: String = "اوه! چیزی پیدا نشد."
) : ApiError

data class InvalidCredentialsError(
    override val httpStatus: Int,
    override val message: String = "نام کاربری یا رمز عبور اشتباهه! یه بار دیگه شانستو امتحان کن."
) : ApiError


data class ServerError(
    override val httpStatus: Int,
    override val message: String = "اوپس! سرور ترکید!"
) : ApiError

data class BadRequestError(
    override val httpStatus: Int,
    override val message: String = "درخواستت یه مشکلی داره، لطفاً چکش کن."
) : ApiError

data class UnauthorizedError(
    override val httpStatus: Int,
    override val message: String = "اجازه دسترسی نداری، لطفاً وارد شو."
) : ApiError

data class ForbiddenError(
    override val httpStatus: Int,
    override val message: String = "اینجا ورود ممنوعه! دسترسی نداری."
) : ApiError

data class ConflictError(
    override val httpStatus: Int,
    override val message: String = "یه تداخلی پیش اومده، دوباره تلاش کن."
) : ApiError

data class GoneError(
    override val httpStatus: Int,
    override val message: String = "این مورد دیگه نیست، حذف شده."
) : ApiError

data class UnsupportedMediaTypeError(
    override val httpStatus: Int,
    override val message: String = "فرمت فایل ساپورت نمیشه، یه چیز دیگه بفرست."
) : ApiError

data class TooManyRequestsError(
    override val httpStatus: Int,
    override val message: String = "خیلی درخواست دادی، یه کم صبر کن."
) : ApiError

data class ServiceUnavailableError(
    override val httpStatus: Int,
    override val message: String = "سرویس فعلاً خوابیده، بعداً بیا."
) : ApiError
