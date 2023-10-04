package com.innosync.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.entity.token.TokenEntity

@Database(
    entities = [
        TokenEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class HookDataBase: RoomDatabase() {
    abstract fun tokenDao(): TokenDao
}