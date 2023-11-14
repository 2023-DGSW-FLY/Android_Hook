package com.innosync.domain.usecase.jobopening

import com.innosync.domain.repository.JobOpeningRepository
import javax.inject.Inject

class JobOpeningGetOneExerciseUseCase @Inject constructor(
    private val repository: JobOpeningRepository
) {

    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        repository.getOneExercise(id)
    }
}