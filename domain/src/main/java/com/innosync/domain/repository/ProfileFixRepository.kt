package com.innosync.domain.repository

import android.graphics.Bitmap


interface ProfileFixRepository {


    suspend fun Fix(
        userAccount: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap?
    )


}

