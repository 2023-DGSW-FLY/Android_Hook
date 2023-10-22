package com.innosync.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UserNameResponse (
    @field:SerializedName("name")
    val name: String,
)