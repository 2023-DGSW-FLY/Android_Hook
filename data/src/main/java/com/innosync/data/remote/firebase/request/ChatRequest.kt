package com.innosync.data.remote.firebase.request
import com.google.firebase.firestore.FieldValue
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import com.google.gson.annotations.SerializedName

data class ChatRequest(
    @field:SerializedName("author")
    val author: String = "밥먹고싶다",
    @field:SerializedName("content")
    val content: String = "리얼크크",
    @ServerTimestamp
    @field:SerializedName("timestamp")
    val timestamp: Timestamp? = null//FieldValue.serverTimestamp()
)
