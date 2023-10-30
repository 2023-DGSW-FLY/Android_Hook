package com.innosync.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.innosync.data.local.dao.FirebaseTokenDao
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.entity.token.FirebaseTokenEntity
import com.innosync.data.local.entity.token.TokenEntity

@Database(
    entities = [
        TokenEntity::class,
        FirebaseTokenEntity::class,
    ],
    version = 3,
    exportSchema = false
)
abstract class HookDataBase: RoomDatabase() {
    abstract fun tokenDao(): TokenDao
    abstract fun firebaseTokenDao(): FirebaseTokenDao
}