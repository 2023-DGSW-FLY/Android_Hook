package com.innosync.domain.repository

interface AuthRepository  {

    suspend fun login(
        userAccount: String,
        password: String
    )

    suspend fun join(
        userAccount: String,
        password: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String
    ): String
}