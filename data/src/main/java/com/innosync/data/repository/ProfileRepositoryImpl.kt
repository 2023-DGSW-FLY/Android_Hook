package com.innosync.data.repository


import android.graphics.Bitmap
import android.util.Log
import com.innosync.data.remote.service.ProfileFixService


import javax.inject.Inject

import com.innosync.data.remote.request.profilefix.ProfileFixRequest
import com.innosync.data.remote.utiles.BitmapRequestBody
import com.innosync.data.remote.utiles.hookApiCall

import com.innosync.domain.repository.ProfileFixRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.MultipartBody

class ProfileRepositoryImpl @Inject constructor(
    private val profileFixService: ProfileFixService
): ProfileFixRepository {

//    override suspend fun image(
//        profileImage: Bitmap?
//    ){
//        val image = BitmapRequestBody(profileImage)
//        Log.d("TAG", "image: ew$profileImage")
//        val bitmapMultipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("image", "profile.jpeg", image)
//        Log.d("TAG", "image: rrr$bitmapMultipartBody")
//
//        runBlocking(Dispatchers.IO) {
//            profileFixService.image(
//                image = bitmapMultipartBody
//            )
//        }
//
//    }
    override suspend fun Fix(
        userAccount: String,
        user_name: String,
        email: String,
        user_info: String,
        github_url: String,
        portfolio_url: String,
        profileImage: Bitmap?
    ) {
        runBlocking(Dispatchers.IO) {
            profileFixService.fix(
                body = ProfileFixRequest(
                    userAccount = userAccount,
                    user_name = user_name,
                    email = email,
                    user_info = user_info,
                    github_url = github_url,
                    portfolio_url = portfolio_url
                )
            )
        }

        if (profileImage != null) {
            runBlocking(Dispatchers.IO) {
                val image = BitmapRequestBody(profileImage)
                Log.d("TAG", "image: ew$profileImage")
                val bitmapMultipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("image", "profile.jpeg", image)
                Log.d("TAG", "image: rrr$bitmapMultipartBody")

                runBlocking(Dispatchers.IO) {
                    profileFixService.image(
                        image = bitmapMultipartBody
                    )
                }
            }
        }
    }





}

