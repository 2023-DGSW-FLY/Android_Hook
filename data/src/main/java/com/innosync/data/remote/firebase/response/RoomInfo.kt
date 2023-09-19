package com.innosync.data.remote.firebase.response

import com.google.gson.annotations.SerializedName

data class RoomInfo(
    @field:SerializedName("timestamp")
    val timestamp: Long = 0,
    @field:SerializedName("roomName")
    val roomName: String = "ee",
    @field:SerializedName("chatRoomUid")
    val chatRoomUid: String = "33",
    @field:SerializedName("check")
    val check: Boolean = false,
    @field:SerializedName("lastMessage")
    val lastMessage: String = "하이",
    @field:SerializedName("users")
    val users: Map<String, Boolean>? = emptyMap(),
    @field:SerializedName("thumbnail")
    val thumbnail: Map<String, String> = emptyMap(),
    @field:SerializedName("key")
    val key: String = ""
)