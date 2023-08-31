package com.innosync.data.repository

import com.innosync.domain.repository.ExampleGetRepository
import javax.inject.Inject

class ExampleGetGetRepositoryImpl @Inject constructor(

): ExampleGetRepository {
    override suspend fun getMessage(): String =
        "test"
}