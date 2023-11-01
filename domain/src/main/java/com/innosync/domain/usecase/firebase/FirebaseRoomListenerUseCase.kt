package com.innosync.domain.usecase.firebase

import com.innosync.domain.model.ChatModel
import com.innosync.domain.model.RoomModel
import com.innosync.domain.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRoomListenerUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) {

    suspend operator fun invoke(
        userId: String,
        chatUid: String,
    ) {
        firebaseRepository.eventRoomListener(
            userId = userId,
            chatUid = chatUid
        )
    }
}