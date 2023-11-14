package com.innosync.domain.usecase.alarm

import com.innosync.domain.repository.AlarmRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AlarmInsertLastCheckUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(value: LocalDateTime) = repository.insertAlarmLastCheck(value)
}