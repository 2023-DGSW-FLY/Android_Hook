package com.innosync.domain.model

import java.time.LocalDateTime

data class HackathonModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
    val stack: List<String>,
    val url: String,
    val status: String,
    val username: String,
    val writer: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)