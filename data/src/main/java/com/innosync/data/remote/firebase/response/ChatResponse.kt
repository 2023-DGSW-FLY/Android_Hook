package com.innosync.data.remote.firebase.response

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @field:SerializedName("author")
    val author: String = "밥먹고싶다",
    @field:SerializedName("content")
    val content: String = "리얼크크",
    @field:SerializedName("timestamp")
    val timestamp: Timestamp = Timestamp.now()
)
