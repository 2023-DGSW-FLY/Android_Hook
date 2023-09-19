package com.innosync.domain.usecase

import com.innosync.domain.model.ChatData
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseChatListenerUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        chatUid: String,
        action: (List<ChatData>) -> Unit
    ) {
        firebaseRepository.eventChatLister(
            chatUid = chatUid,
            action = action
        )
    }
}