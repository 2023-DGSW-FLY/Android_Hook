package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningInsertEatUseCase @Inject constructor(
    private val jobOpeningRepository: JobOpeningRepository
) {

    suspend operator fun invoke(
        foodName: String?,
        title: String,
        content: String,
        place: String,
    ) = kotlin.runCatching {
        jobOpeningRepository.insertEat(
            foodName = foodName,
            title = title,
            content = content,
            place = place
        )
    }
}