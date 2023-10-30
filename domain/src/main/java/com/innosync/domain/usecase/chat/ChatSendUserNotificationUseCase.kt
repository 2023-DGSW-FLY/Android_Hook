package com.innosync.domain.usecase.chat

import com.innosync.domain.repository.ChatRepository
import javax.inject.Inject

class ChatSendUserNotificationUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        targetId: String
    ) = kotlin.runCatching {
        repository.sendNotification(
            title = title,
            content = content,
            targetId = targetId
        )
    }
}