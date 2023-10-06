package com.innosync.domain.repository


import com.innosync.domain.model.ChatModel
import com.innosync.domain.model.RoomModel

interface FirebaseRepository {

    suspend fun getRoomList(
        userId: String,
        action: (List<RoomModel>) -> Unit
    )

    suspend fun insertRoom(
        userId: String,
        targetId: String
    )

    suspend fun eventChatLister(
        chatUid: String,
        action: (List<ChatModel>) -> Unit
    )

    suspend fun sendMessage(
        userId: String,
        chatUid: String,
        content: String
    )
}