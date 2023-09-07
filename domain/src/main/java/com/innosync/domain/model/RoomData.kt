package com.innosync.domain.model

data class RoomData(
    val timestamp: Long = 0,
    val roomName: String = "ee",
    val chatRoomUid: String = "33",
    val check: Boolean = false,
    val lastMessage: String = "하이",
    val users: Map<String, Boolean>? = emptyMap(),
    val thumbnail: Map<String, String> = emptyMap(),
    val key: String = ""
)
