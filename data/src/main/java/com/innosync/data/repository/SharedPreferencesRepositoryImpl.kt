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

    override suspend fun getAlarm(): Boolean =
        sharedPreferences.getBoolean("alarm", true) ?: true

    override suspend fun setAlarm(value: Boolean) =
        sharedPreferences.edit().putBoolean("alarm", value).apply()
}