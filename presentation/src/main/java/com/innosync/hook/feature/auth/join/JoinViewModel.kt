package com.innosync.hook.feature.auth.join

import androidx.lifecycle.viewModelScope
import com.innosync.domain.usecase.auth.JoinUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.feature.auth.login.LoginViewModel.Companion.ON_CLICK_JOIN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JoinViewModel @Inject constructor(
    private val joinUseCase: JoinUseCase
): BaseViewModel() {

    fun onClickJoin() =
        viewEvent(ON_CLICK_JOIN)

    fun join(
        userAccount: String,
        password: String,
        userName: String,
        email: String,
        userInfo: String,
        githubURL: String,
        portfolioURL: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        joinUseCase.invoke(
            userAccount = userAccount,
            password = password,
            userName = userName,
            email = email,
            userInfo = userInfo,
            githubURL = githubURL,
            portfolioURL = portfolioURL
        ).onSuccess {
            viewModelScope.launch(Dispatchers.Main) {
                viewEvent(ON_SUCCESS)
            }
        }.onFailures {
            viewModelScope.launch(Dispatchers.Main) {
                viewEvent(ON_FAILURE)
            }
        }
    }

    companion object {
        const val ON_CLICK_JOIN = 0
        const val ON_SUCCESS = 1
        const val ON_FAILURE = 2
    }
}
