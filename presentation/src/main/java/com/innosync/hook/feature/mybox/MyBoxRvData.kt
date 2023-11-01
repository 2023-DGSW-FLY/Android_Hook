package com.innosync.hook.feature.mybox

import java.time.LocalDateTime

public
data class MyBoxRvData (
    val content: String,
    val status: String,
    val date: LocalDateTime,
    val id : Int,
    val type: String
)
{
    var statusName : String = ""
        get() = if(status == "matching") "매칭중" else "매칭완료"
}