package com.innosync.data.remote.utiles

import android.util.Log
import com.google.gson.Gson
import com.innosync.data.remote.response.ErrorResponse
import com.innosync.domain.exception.BadRequestException
import com.innosync.domain.exception.ConflictException
import com.innosync.domain.exception.ExpiredRefreshTokenException
import com.innosync.domain.exception.NoConnectivityException
import com.innosync.domain.exception.NoInternetException
import com.innosync.domain.exception.NotFoundException
import com.innosync.domain.exception.OtherHttpException
import com.innosync.domain.exception.ServerException
import com.innosync.domain.exception.TimeOutException
import com.innosync.domain.exception.TooManyRequestsException
import com.innosync.domain.exception.UnknownException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val EXPIRED_TOKEN_MESSAGE = "만료된 토큰"

suspend inline fun <T> hookApiCall(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        val message: String = getErrorMessage(e)

        throw when (e.code()) {
            400 -> BadRequestException(
                message = message,
                fieldErrors = emptyList()
            )
//            401 -> {
//                if (e.message == EXPIRED_TOKEN_MESSAGE) {
//                    ExpiredRefreshTokenException()
//                } else {
//                    UnAuthorizedException(
//                        message = message
//                    )
//                }
//            }
            403 -> (
                ExpiredRefreshTokenException()
            )

            404 -> NotFoundException(
                message = message,
            )
            408 -> TimeOutException(
                message = message
            )
            409 -> ConflictException(
                message = message
            )
            429 -> TooManyRequestsException(
                message = message,
            )
            500, 501, 502, 503 -> ServerException(
                message = message,
            )
            else -> OtherHttpException(
                code = e.code(),
                message = message,
            )
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(
            message = e.message,
        )
    } catch (e: ExpiredRefreshTokenException) {
        throw e
    } catch (e: NoInternetException) {
        throw NoInternetException()
    } catch (e: NoConnectivityException) {
        throw NoConnectivityException()
    } catch (e: Exception) {
        Log.d("LOG", "hookApiCall: $e")
        throw UnknownException(
            message = e.message,
        )
    }
}

private const val UNKNOWN_ERROR_MESSAGE = "알 수 없는 오류가 발생했습니다."

fun getErrorMessage(exception: HttpException): String {
    val errorStr = exception.response()?.errorBody()?.string()
    val errorDto: ErrorResponse? = Gson().fromJson(
        errorStr, ErrorResponse::class.java
    )

    return errorDto?.Fail ?: UNKNOWN_ERROR_MESSAGE
}