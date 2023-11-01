package com.innosync.domain.usecase.firebase

import com.innosync.domain.model.ChatModel
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseChatListenerUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        chatUid: String,
        action: (List<ChatModel>) -> Unit
    ) {
        firebaseRepository.eventChatLister(
            chatUid = chatUid,
            action = action
        )
    }
}