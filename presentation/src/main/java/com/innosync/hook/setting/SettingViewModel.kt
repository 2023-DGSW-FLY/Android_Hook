package com.innosync.hook.setting

import com.innosync.domain.usecase.alarm.AlarmGetChatStateUseCase
import com.innosync.domain.usecase.alarm.AlarmGetStateUseCase
import com.innosync.domain.usecase.alarm.AlarmSetStateUseCase
import com.innosync.domain.usecase.token.DeleteTokenUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getStateUseCase: AlarmGetChatStateUseCase,
    private val alarmSetStateUseCase: AlarmSetStateUseCase,
    private val tokenDeleteTokenUseCase: DeleteTokenUseCase
): BaseViewModel() {

    private val _settingState =  MutableStateFlow(false)
    val settingState = _settingState.asStateFlow()

    private val _loadState =  MutableStateFlow(false)
    val loadState = _loadState.asStateFlow()

    private val _logoutState =  MutableStateFlow(false)
    val logoutState = _logoutState.asStateFlow()


    fun load() = launchIO {
        _settingState.value = getStateUseCase.invoke()
        _loadState.value = true
    }

    fun setNotification(state: Boolean) = launchIO {
        alarmSetStateUseCase.invoke(state)
    }

    fun logout() = launchIO {
        tokenDeleteTokenUseCase.invoke().onSuccess {
            _logoutState.value = true
        }
    }

    fun onClickLogout() =
        viewEvent(ON_CLICK_LOGOUT)

    fun onClickNotice() =
        viewEvent(ON_CLICK_NOTICE)

    companion object {
        const val ON_CLICK_LOGOUT = 0
        const val ON_CLICK_NOTICE = 1
    }

}