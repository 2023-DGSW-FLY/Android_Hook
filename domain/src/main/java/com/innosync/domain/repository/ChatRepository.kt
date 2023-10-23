package com.innosync.domain.repository

import com.innosync.domain.model.UserModel

interface ChatRepository {

    suspend fun getThese(users: List<String>): Map<String, String>

    suspend fun getUserName(userId: String): String
}