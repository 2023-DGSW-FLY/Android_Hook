package com.innosync.domain.repository

interface ExampleGetRepository {
    suspend fun getMessage(): String
}