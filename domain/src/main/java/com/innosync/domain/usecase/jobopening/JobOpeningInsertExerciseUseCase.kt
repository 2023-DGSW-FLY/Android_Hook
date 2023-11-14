package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningInsertExerciseUseCase @Inject constructor(
    private val jobOpeningRepository: JobOpeningRepository
) {
    suspend operator fun invoke(
        title: String,
        content: String,
        place: String,
        exercise: String,
        dateTime: String,
    ) = kotlin.runCatching {
        jobOpeningRepository.insertExercise(
            title = title,
            content = content,
            place = place,
            dateTime = dateTime,
            exercise = exercise
        )
    }
}