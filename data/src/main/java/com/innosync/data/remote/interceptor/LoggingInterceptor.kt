package com.innosync.data.remote.interceptor

import android.util.Log
import com.innosync.domain.exception.ExpiredRefreshTokenException
import com.innosync.domain.usecase.token.DeleteTokenUseCase
import com.innosync.domain.usecase.token.FetchTokenUseCase
import com.innosync.domain.usecase.token.GetTokenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class LoggingInterceptor @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val fetchTokenUseCase: FetchTokenUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
) : Interceptor {

    private val TOKEN_ERROR = 403
    private val TOKEN_HEADER = "Authorization"

    private lateinit var token: String

    override fun intercept(chain: Interceptor.Chain): Response {
        var failed = false
        runBlocking(Dispatchers.IO) {
            getTokenUseCase().onSuccess {
                token = it.token
            }.onFailure {
                Log.d("로그", "intercept: $it")
                failed = true
            }
        }
        if (failed) {
            return Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .code(TOKEN_ERROR)
                .message("세션이 만료되었습니다.")
                .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                .build()
        }
        val request: Request = chain.request().newBuilder()
            .addHeader(TOKEN_HEADER, "Bearer $token")
            .build()
        var response = chain.proceed(request)
        Log.d("TAG", "intercept: ${response.code}")
        if (response.code == TOKEN_ERROR) {
            response.close()
            runBlocking {
                fetchTokenUseCase().onSuccess {
                    Log.d("TAG", "intercept: $it")
                    val newBase: Request = chain.request().newBuilder()
                        .addHeader(TOKEN_HEADER, "Bearer ${it.token}")
                        .build()
                    response = chain.proceed(newBase)
                    Log.d("TAG", "intercept: ${response.code}")
                    if (response.code == TOKEN_ERROR) {
                        deleteTokenUseCase.invoke()
                        response = Response.Builder()
                            .request(request)
                            .protocol(Protocol.HTTP_1_1)
                            .code(TOKEN_ERROR)
                            .message("세션이 만료되었습니다.")
                            .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                            .build()
                    }

                }.onFailure {
                    deleteTokenUseCase.invoke()
                    response = Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(TOKEN_ERROR)
                        .message("세션이 만료되었습니다.")
                        .body("{\"status\":401,\"message\":\"세션이 만료되었습니다.\"}".toResponseBody())
                        .build()
                }
            }
        }
        return response
    }
}