package com.innosync.domain.usecase.sharedpreferences

import com.innosync.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesSetAlarmUseCase @Inject constructor(
    private val repository: SharedPreferencesRepository
) {

    suspend operator fun invoke(value: Boolean) = repository.setAlarm(value)
}