package com.innosync.domain.model

import java.time.LocalDateTime

data class AlarmModel (
    val content: String,
    val regDate: LocalDateTime,
    val type: String,
)