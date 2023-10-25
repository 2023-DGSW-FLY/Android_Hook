package com.innosync.domain.usecase.sharedpreferences

import com.innosync.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesGetAlarmUseCase @Inject constructor(
    private val repository: SharedPreferencesRepository
) {

    suspend operator fun invoke() = repository.getAlarm()
}