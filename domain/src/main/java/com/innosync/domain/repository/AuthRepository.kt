package com.innosync.domain.repository

import android.graphics.Bitmap

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
        portfolioURL: String,
        profileImage: Bitmap
    ): String


    suspend fun logout()
}