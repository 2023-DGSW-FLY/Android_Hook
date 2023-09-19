package com.innosync.hook.feature.chat.message

import androidx.lifecycle.viewModelScope
import com.innosync.domain.model.ChatData
import com.innosync.domain.usecase.FirebaseChatListenerUseCase
import com.innosync.domain.usecase.FirebaseSendMessageUseCase
import com.innosync.hook.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val firebaseChatListenerUseCase: FirebaseChatListenerUseCase,
    private val firebaseSendMessageUseCase: FirebaseSendMessageUseCase
): BaseViewModel() {

    fun addChatEventListener(
        chatUid: String,
        action: (List<ChatData>) -> Unit
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

    fun onClickSend() =
        viewEvent(ON_CLICK_SEND)

    companion object {
        const val ON_CLICK_SEND = 0
    }

}