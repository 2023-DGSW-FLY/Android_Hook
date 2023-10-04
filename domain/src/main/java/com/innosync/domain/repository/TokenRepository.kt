package com.innosync.domain.repository

import com.innosync.domain.model.Token

interface TokenRepository {

    suspend fun getToken(): Token

    suspend fun fetchToken(): Token

    suspend fun deleteToken()

    suspend fun checkToken(): Boolean

}