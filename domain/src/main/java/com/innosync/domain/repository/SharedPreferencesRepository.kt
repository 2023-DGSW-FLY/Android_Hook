package com.innosync.domain.repository

interface SharedPreferencesRepository {

    suspend fun get(): String

    suspend fun insert(value: String)

    suspend fun getAlarm(): Boolean

    suspend fun setAlarm(value: Boolean)
}