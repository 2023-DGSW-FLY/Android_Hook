package com.innosync.domain.usecase.auth

import android.graphics.Bitmap
import com.innosync.domain.repository.AuthRepository
import javax.inject.Inject

class JoinUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        userAccount: String,
        password: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap
    ) = kotlin.runCatching {
        authRepository.join(
            userAccount = userAccount,
            password = password,
            userName = userName,
            email = email,
            userInfo = userInfo,
            githubURL = githubURL,
            portfolioURL = portfolioURL,
            profileImage = profileImage
        )
    }
}