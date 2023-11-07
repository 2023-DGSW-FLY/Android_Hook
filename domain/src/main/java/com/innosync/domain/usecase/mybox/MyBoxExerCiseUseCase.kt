package com.innosync.domain.usecase.mybox

import com.innosync.domain.repository.MyBoxRepository
import javax.inject.Inject
import kotlin.jvm.internal.Intrinsics.Kotlin

class MyBoxExerciseUseCase @Inject constructor(
    private val myBoxRepository: MyBoxRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        myBoxRepository.getExercise()
    }
}