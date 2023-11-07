package com.innosync.hook.feature.auth.join

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.innosync.domain.usecase.auth.JoinUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_CLICK_JOIN
import com.innosync.hook.feature.chat.ChatFragment.Companion.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.BufferedSink
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
): BaseViewModel() {

    fun onClickJoin() {
        viewEvent(ON_CLICK_JOIN)
    }

    fun onClickUpload() {
        viewEvent(ON_CLICK_UPLOAD)
    }

    fun join(
        userAccount: String,
        password: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap
    ) {
        Log.d(TAG, "join: called")
//        val image = BitmapRequestBody(profileImage)
//        val bitmapMultipartBody: MultipartBody.Part = MultipartBody.Part.createFormData("image", "profile.jpeg", image)

        viewModelScope.launch(Dispatchers.IO) {
            joinUseCase.invoke(
                userAccount = userAccount,
                password = password,
                userName = userName,
                email = email,
                userInfo = userInfo,
                githubURL = githubURL,
                portfolioURL = portfolioURL,
                profileImage = profileImage
            ).onSuccess {
                Log.d(TAG, "join Success: $it")
                viewModelScope.launch(Dispatchers.Main) {
                    viewEvent(ON_SUCCESS)
                }
            }.onFailures {
                Log.d(TAG, "join Failed: $it")
                viewModelScope.launch(Dispatchers.Main) {
                    viewEvent(ON_FAILURE)
                }
            }
        }
    }

    companion object {
        const val ON_CLICK_JOIN = 0
        const val ON_SUCCESS = 1
        const val ON_FAILURE = 2
        const val ON_CLICK_UPLOAD = 3
    }
}
