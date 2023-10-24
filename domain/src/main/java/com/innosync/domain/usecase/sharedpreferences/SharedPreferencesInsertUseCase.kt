package com.innosync.domain.usecase.sharedpreferences

import com.innosync.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesInsertUseCase @Inject constructor(
    private val repository: SharedPreferencesRepository
) {

    suspend operator fun invoke(value: String) = repository.insert(value)
}