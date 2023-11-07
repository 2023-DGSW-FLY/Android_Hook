package com.innosync.domain.usecase.profile

import android.graphics.Bitmap
import android.util.Log
import com.innosync.domain.repository.ProfileFixRepository
import javax.inject.Inject

class ProfileFixUseCase @Inject constructor(
    private val profileRepository: ProfileFixRepository
) {


    suspend operator fun invoke(
        userAccount: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap?
        )= kotlin.runCatching {
        Log.d("TAG", "invoke: $profileImage")
            profileRepository.Fix(
                userAccount = userAccount,
                userName = userName,
                email = email,
                userInfo = userInfo,
                githubURL = githubURL,
                portfolioURL = portfolioURL,
                profileImage = profileImage
            )

    }
}