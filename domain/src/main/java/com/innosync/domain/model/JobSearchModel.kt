package com.innosync.domain.model

import java.time.LocalDateTime

data class JobSearchModel(
    val id: Int,
    val content: String,
    val stack: String,
    val url: String,
    val status: String,
    val writer: String,
    val userId: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)