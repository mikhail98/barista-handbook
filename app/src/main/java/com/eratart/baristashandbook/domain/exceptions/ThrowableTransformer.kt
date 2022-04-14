package com.eratart.baristashandbook.domain.exceptions

import okhttp3.internal.http.HTTP_NOT_FOUND
import okhttp3.internal.http.HTTP_UNAUTHORIZED
import retrofit2.HttpException
import java.net.SocketTimeoutException

object ThrowableTransformer {
    fun transform(e: Throwable): Throwable {
        return when (e) {
            is HttpException -> {
                when (val code = e.code()) {
                    HTTP_UNAUTHORIZED -> UnauthorizedException(code)
                    HTTP_NOT_FOUND -> NotFoundException(code)
                    else -> BackendException(code, e.message())
                }
            }
            is SocketTimeoutException -> TimeoutException()
            else -> OtherApiException(e.message.orEmpty())
        }
    }
}