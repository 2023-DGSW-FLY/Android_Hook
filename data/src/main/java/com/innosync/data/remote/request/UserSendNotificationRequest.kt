package com.innosync.data.remote.request

import com.google.gson.annotations.SerializedName

data class UserSendNotificationRequest(
    @SerializedName("targetUserId")
    val targetId: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,

)

