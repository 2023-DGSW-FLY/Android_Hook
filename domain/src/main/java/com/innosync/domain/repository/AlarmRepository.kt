package com.innosync.domain.repository

import com.innosync.domain.model.AlarmModel
import java.time.LocalDateTime

interface AlarmRepository {

    suspend fun getChatState(): String

    suspend fun insertChatState(value: String)

    suspend fun getAlarmState(): Boolean

    suspend fun setAlarmState(value: Boolean)

    suspend fun getAlarmAll(): List<AlarmModel>

    suspend fun getAlarmLastCheck(): LocalDateTime

    suspend fun insertAlarmLastCheck(value: LocalDateTime)
}