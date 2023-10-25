package com.innosync.hook.feature.mybox

import java.time.LocalDateTime

data class MyBoxRvData (
    val content: String,
    val status: String,
    val date: LocalDateTime,
    val id : Int
)