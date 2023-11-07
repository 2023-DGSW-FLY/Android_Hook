package com.innosync.data.repository

import android.graphics.Bitmap
import android.util.Log
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.data.remote.request.UserJoinRequest
import com.innosync.data.remote.request.UserLoginRequest
import com.innosync.data.remote.service.LoginService
import com.innosync.data.remote.utiles.BitmapRequestBody
import com.innosync.data.remote.utiles.hookApiCall
import com.innosync.domain.repository.AuthRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val tokenDao: TokenDao
): AuthRepository {

    override suspend fun login(userAccount: String, password: String) {
        Log.d("TAG", "login: qweqwqwe")
        loginService.login(
            UserLoginRequest(
                userAccount = userAccount,
                password = password
            )
        ).data.let {
            Log.d("TAG", "login: $it")
            tokenDao.insert(
                TokenEntity(
                    token = it.accessToken,
                    refreshToken = it.refreshToken
                )
            )
        }
    }

    override suspend fun join(
        userAccount: String,
        password: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap
    ): String {
        val image = BitmapRequestBody(profileImage)
        val bitmapMultipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("image", "profile.jpeg", image)
        Log.d("TAG", "join: $bitmapMultipartBody")
        return loginService.join(
            image = bitmapMultipartBody,
            body = UserJoinRequest(
                userAccount = userAccount,
                password = password,
                userName = userName,
                email = email,
                userInfo = userInfo,
                githubURL = githubURL,
                portfolioURL = portfolioURL
            )
        ).success
    }


}