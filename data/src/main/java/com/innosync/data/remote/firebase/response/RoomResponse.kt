package com.innosync.data.remote.firebase.response

import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName

data class RoomResponse(
    @field:SerializedName("timestamp")
    val timestamp: Timestamp = Timestamp.now(),
    @field:SerializedName("roomName")
    val roomName: String = "ee",
    @field:SerializedName("chatRoomUid")
    val chatRoomUid: String = "33",
    @field:SerializedName("check")
    val check: Boolean = false,
    @field:SerializedName("lastMessage")
    val lastMessage: String = "하이",
    @field:SerializedName("users")
    val users: Map<String, Boolean>? = emptyMap()
)