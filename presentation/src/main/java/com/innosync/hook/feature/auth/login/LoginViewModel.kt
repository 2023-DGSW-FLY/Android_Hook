package com.innosync.hook.feature.auth.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.innosync.domain.usecase.auth.LoginUseCase
import com.innosync.domain.usecase.token.GetTokenUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val tokenUseCase: GetTokenUseCase
): BaseViewModel() {

    private val _tokenState = MutableStateFlow(false)
    val tokenState = _tokenState.asStateFlow()

    fun basicLogin(
        userAccount: String,
        password: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        loginUseCase.invoke(
            userAccount = userAccount,
            password = password
        ).onSuccess {
            viewModelScope.launch(Dispatchers.Main) {
                viewEvent(ON_SUCCESS_LOGIN)
            }
        }.onFailure {
            Log.d("TAG", "basicLogin: $it")
            viewModelScope.launch(Dispatchers.Main) {
                viewEvent(ON_FAILED_LOGIN)
            }
        }
    }

    fun tokenCheck() = launchIO {
        tokenUseCase.invoke(

        ).onSuccess {
            _tokenState.value = true
        }.onFailures {

        }
    }


    fun onClickLogin() = viewEvent(ON_CLICK_LOGIN)

    fun onClickJoin() = viewEvent(ON_CLICK_JOIN)

    fun onClickKakao() = viewEvent(ON_CLICK_KAKAO)


    companion object {
        const val ON_CLICK_LOGIN = 0
        const val ON_CLICK_JOIN = 1
        const val ON_CLICK_KAKAO = 2
        const val ON_SUCCESS_LOGIN = 100
        const val ON_FAILED_LOGIN = 101
    }
}