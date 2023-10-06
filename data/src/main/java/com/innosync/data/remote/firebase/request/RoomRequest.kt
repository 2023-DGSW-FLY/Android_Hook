package com.innosync.data.remote.firebase.request

import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.annotations.SerializedName
import com.google.firebase.Timestamp

internal data class RoomRequest(
    @field:SerializedName("timestamp")
    @ServerTimestamp
    val timestamp: Timestamp? = Timestamp.now(),
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