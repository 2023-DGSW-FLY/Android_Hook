package com.innosync.data.remote.response

import com.google.gson.annotations.SerializedName

data class TestResponse(
    @field:SerializedName("Success")
    val test: String
)