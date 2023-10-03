package com.innosync.domain.model

import java.time.LocalDateTime

data class JobSearchModel(
    val id: Int,
    val title: String,
    val content: String,
    val stack: List<String>,
    val url: String,
    val status: String,
    val writer: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime
)