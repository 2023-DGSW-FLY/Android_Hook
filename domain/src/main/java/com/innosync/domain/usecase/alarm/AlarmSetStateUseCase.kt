package com.innosync.domain.usecase.alarm

import com.innosync.domain.repository.AlarmRepository
import javax.inject.Inject

class AlarmSetStateUseCase @Inject constructor(
    private val repository: AlarmRepository
) {

    suspend operator fun invoke(value: Boolean) = repository.setAlarmState(value)
}