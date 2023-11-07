package com.innosync.hook.feature.profile.fix

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.UserModel
import com.innosync.domain.usecase.profile.ProfileFixUseCase
import com.innosync.domain.usecase.user.UserGetInfoUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileFixViewModel @Inject constructor(
    private val profileFixUseCase: ProfileFixUseCase,
    private val userGetInfoUseCase: UserGetInfoUseCase
) : BaseViewModel() {

    private val _profileFixData = MutableLiveData<UserModel>()
    val profileFixData : LiveData<UserModel> = _profileFixData

    private var _btnTrue = MutableLiveData(false)

    fun onClickComplete() {
        if (_btnTrue.value == true) {
            return
        }
        else{
            _btnTrue.value = true
        }
        Log.d("TAG", "onClickComplete: ")
        viewEvent(ON_CLICK_COMPLETE)
    }

    fun onClickImage() {
        viewEvent(ON_CLICK_IMAGE)
        Log.d("TAG", "onClickImage: ")
    }

    fun failuresBtn(){
        _btnTrue.value = false
    }

    fun loadInfo() = launchIO {
        userGetInfoUseCase.invoke()
            .onSuccess {
                launchMain {
                    _profileFixData.value = it
                    Log.d("TAG", "loadInfo: $it")
                }

            }.onFailures {
                Log.d("TAG", "loadInfo: $it")
            }
    }

    fun fix(
        userAccount: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String,
        profileImage: Bitmap?
    ) {
        launchIO {
            profileFixUseCase.invoke(
                userAccount = userAccount,
                userName = userName,
                email = email,
                userInfo = userInfo,
                githubURL = githubURL,
                portfolioURL = portfolioURL,
                profileImage = profileImage
            ).onSuccess {
                Log.d("ProfileFixViewModel", "fix: Success")
                launchMain {
                    viewEvent(ON_SUCCESS)
                }
            }.onFailure { error ->
                Log.d("ProfileFixViewModel", "fix: $error")
                launchMain {
                    viewEvent(ON_FAILURE)
                }
            }
        }
    }




    companion object {
        const val ON_CLICK_COMPLETE = 1
        const val ON_CLICK_IMAGE = 2
        const val ON_SUCCESS = 3
        const val ON_FAILURE = 4

    }

}