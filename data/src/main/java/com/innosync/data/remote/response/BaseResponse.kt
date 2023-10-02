package com.innosync.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T> (
    @field:SerializedName("data")
    val data: T,
)