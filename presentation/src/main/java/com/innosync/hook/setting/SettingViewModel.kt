package com.innosync.hook.setting

import com.innosync.domain.usecase.alarm.AlarmGetChatStateUseCase
import com.innosync.domain.usecase.alarm.AlarmGetStateUseCase
import com.innosync.domain.usecase.alarm.AlarmSetStateUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getStateUseCase: AlarmGetChatStateUseCase,
    private val alarmSetStateUseCase: AlarmSetStateUseCase
): BaseViewModel() {

    private val _settingState =  MutableStateFlow(false)
    val settingState = _settingState.asStateFlow()

    private val _loadState =  MutableStateFlow(false)
    val loadState = _loadState.asStateFlow()


    fun load() = launchIO {
        _settingState.value = getStateUseCase.invoke()
        _loadState.value = true
    }

    fun setNotification(state: Boolean) = launchIO {
        alarmSetStateUseCase.invoke(state)
    }

}