package com.innosync.hook.feature.chat.message

import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.ChatModel
import com.innosync.domain.usecase.FirebaseChatListenerUseCase
import com.innosync.domain.usecase.FirebaseSendMessageUseCase
import com.innosync.domain.usecase.chat.ChatGetUserNameUseCase
import com.innosync.domain.usecase.chat.ChatSendUserNotificationUseCase
import com.innosync.domain.usecase.alarm.AlarmInsertChatStateUseCase
import com.innosync.hook.base.BaseViewModel
import com.innosync.hook.util.launchIO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val firebaseChatListenerUseCase: FirebaseChatListenerUseCase,
    private val firebaseSendMessageUseCase: FirebaseSendMessageUseCase,
    private val userGetUserNameUseCase: ChatGetUserNameUseCase,
    private val alarmInsertChatStateUseCase: AlarmInsertChatStateUseCase,
    private val chatSendUserNotificationUseCase: ChatSendUserNotificationUseCase
): BaseViewModel() {

    private val _userIdState = MutableStateFlow<String>("")
    val userIdState = _userIdState.asStateFlow()

    fun addChatEventListener(
        chatUid: String,
        action: (List<ChatModel>) -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        firebaseChatListenerUseCase.invoke(
            chatUid = chatUid,
            action = action
        )
    }

    fun sendMessage(
        userId: String,
        chatUid: String,
        content: String
    ) = viewModelScope.launch(Dispatchers.IO) {
        firebaseSendMessageUseCase.invoke(
            userId = userId,
            chatUid = chatUid,
            content = content
        )
    }

    fun sendNotification(
        title: String,
        content: String,
        targetId: String
    ) = launchIO {
        chatSendUserNotificationUseCase.invoke(
            title = title,
            content = content,
            targetId = targetId
        )
    }

    fun loadUserName(
        userId: String
    ) = launchIO {
        userGetUserNameUseCase.invoke(
            userId
        ).onSuccess {
            _userIdState.value = it
        }.onFailures {

        }
    }

    fun insertChat(
        targetId: String
    ) = launchIO {
        alarmInsertChatStateUseCase(targetId)
    }

    fun onClickSend() =
        viewEvent(ON_CLICK_SEND)

    companion object {
        const val ON_CLICK_SEND = 0
    }

}