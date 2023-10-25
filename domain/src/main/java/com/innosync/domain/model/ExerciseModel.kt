package com.innosync.domain.model

import java.time.LocalDateTime

data class ExerciseModel(
    val id: Int,
    val title: String,
    val content: String,
    val place: String,
    val dateTime: String,
    val username: String,
    val writer: String,
    val userId: String,
    val status: String,
    val exercise: String,
    val regDate: LocalDateTime,
    val modDate: LocalDateTime,
)
