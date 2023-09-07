package com.innosync.domain.repository


import com.innosync.domain.model.RoomData

interface FirebaseRepository {

    suspend fun getRoomList(
        userId: String,
        action: (List<RoomData>) -> Unit
    )
}