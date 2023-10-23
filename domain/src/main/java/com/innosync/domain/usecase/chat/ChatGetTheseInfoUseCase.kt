package com.innosync.domain.usecase.chat

import com.innosync.domain.repository.ChatRepository
import com.innosync.domain.repository.UserRepository
import javax.inject.Inject

class ChatGetTheseInfoUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(users: List<String>) = kotlin.runCatching {
        chatRepository.getThese(users)
    }
}