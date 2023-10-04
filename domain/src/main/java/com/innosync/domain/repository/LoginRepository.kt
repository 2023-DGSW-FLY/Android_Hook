package com.innosync.domain.repository

interface LoginRepository  {

    suspend fun login(
        userAccount: String,
        password: String
    )
}