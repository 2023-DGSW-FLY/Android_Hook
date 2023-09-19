package com.innosync.domain.repository


import com.innosync.domain.model.ChatData
import com.innosync.domain.model.RoomData

interface FirebaseRepository {

    suspend fun getRoomList(
        userId: String,
        action: (List<RoomData>) -> Unit
    )

    suspend fun insertRoom(
        userId: String,
        targetId: String
    )

    suspend fun eventChatLister(
        chatUid: String,
        action: (List<ChatData>) -> Unit
    )

    suspend fun sendMessage(
        userId: String,
        chatUid: String,
        content: String
    )
}