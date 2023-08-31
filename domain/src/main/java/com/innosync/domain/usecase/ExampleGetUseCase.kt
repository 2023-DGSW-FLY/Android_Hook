package com.innosync.domain.usecase

import com.innosync.domain.repository.ExampleGetRepository
import javax.inject.Inject

class ExampleGetUseCase @Inject constructor(
    private val exampleRepository: ExampleGetRepository
) {

    suspend operator fun invoke(param: Param) = kotlin.runCatching {
        exampleRepository.getMessage()
    }

    data class Param(
        val message: String
    )

}