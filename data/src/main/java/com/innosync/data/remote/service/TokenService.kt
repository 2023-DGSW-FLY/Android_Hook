package com.innosync.data.remote.service

import com.innosync.data.remote.request.TokenRequest
import com.innosync.data.remote.response.BaseResponse
import com.innosync.data.remote.response.token.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenService {


    @POST("/api/v1/users/refresh")
    suspend fun token (
        @Body token: TokenRequest
    ) : BaseResponse<TokenResponse>
}