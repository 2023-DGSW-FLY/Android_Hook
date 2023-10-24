package com.innosync.data.repository

import android.content.SharedPreferences
import com.innosync.domain.repository.SharedPreferencesRepository
import javax.inject.Inject

class SharedPreferencesRepositoryImpl @Inject constructor(
   private val sharedPreferences: SharedPreferences
): SharedPreferencesRepository {
    override suspend fun get(): String =
        sharedPreferences.getString("chat", "") ?: ""

    override suspend fun insert(value: String) =
        sharedPreferences.edit().putString("chat", value).apply()
}