package com.innosync.hook.feature.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.innosync.domain.model.UserModel
import com.innosync.domain.usecase.user.UserGetInfoUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import com.innosync.hook.util.launchMain
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userGetInfoUseCase: UserGetInfoUseCase
) :BaseViewModel() {

    private val _profileData = MutableLiveData<UserModel>()
    val profileData : LiveData<UserModel> = _profileData

    fun loadInfo() = launchIO {
        userGetInfoUseCase.invoke()
            .onSuccess {
                launchMain {
                    _profileData.value = it
                }
            }.onFailures {
                Log.d("TAG", "loadInfo: $it")
            }
    }

    fun onClickCorrection() =
        viewEvent(ON_CLICK_CORRECTION)


    companion object{
        const val ON_CLICK_CORRECTION = 1
    }
}