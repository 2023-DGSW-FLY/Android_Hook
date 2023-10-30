package com.innosync.domain.usecase.alarm

import com.innosync.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmGetAllUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke() = kotlin.runCatching {
        repository.getAlarmAll()
    }
}