package com.innosync.domain.repository

import com.innosync.domain.model.FirebaseTokenModel
import com.innosync.domain.model.Token

interface FirebaseTokenRepository {

    suspend fun getToken(): FirebaseTokenModel

    suspend fun insert(token: String)

}