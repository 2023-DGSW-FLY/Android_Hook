package com.innosync.domain.usecase.chat

import com.innosync.domain.repository.ChatRepository
import javax.inject.Inject

class ChatGetUserNameUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(userId: String) = kotlin.runCatching {
        chatRepository.getUserName(userId)
    }
}