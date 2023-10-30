package com.innosync.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.innosync.data.remote.mapper.toModels
import com.innosync.data.remote.service.AlarmService
import com.innosync.domain.model.AlarmModel
import com.innosync.domain.repository.AlarmRepository
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

class AlarmRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val alarmService: AlarmService
): AlarmRepository {
    override suspend fun getChatState(): String =
        sharedPreferences.getString("chat", "") ?: ""

    override suspend fun insertChatState(value: String) =
        sharedPreferences.edit().putString("chat", value).apply()

    override suspend fun getAlarmState(): Boolean =
        sharedPreferences.getBoolean("alarm", true) ?: true

    override suspend fun setAlarmState(value: Boolean) =
        sharedPreferences.edit().putBoolean("alarm", value).apply()

    override suspend fun getAlarmAll(): List<AlarmModel> =
        alarmService.get().data.toModels()

    override suspend fun getAlarmLastCheck(): LocalDateTime {
        val timestamp = sharedPreferences.getLong("time", 0) ?: 0
//        Log.d("TAG", "getAlarmLastCheck: $timestamp")
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(9))
    }


    override suspend fun insertAlarmLastCheck(value: LocalDateTime) {
//        Log.d("TAG", "insertAlarmLastCheck: ${value.toEpochSecond(ZoneOffset.ofHours(9))}")
        sharedPreferences.edit().putLong("time", value.toEpochSecond(ZoneOffset.ofHours(9))).apply()
    }


}