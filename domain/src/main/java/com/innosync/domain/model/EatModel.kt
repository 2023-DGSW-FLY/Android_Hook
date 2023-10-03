package com.innosync.domain.model

import java.time.LocalDateTime

data class EatModel(
    val id: Int,
    val title: String,
    val content: String,
    val place: String,
    val dateTime: String,
    val status: String,
    val writer: String,
    val userId: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime,
)