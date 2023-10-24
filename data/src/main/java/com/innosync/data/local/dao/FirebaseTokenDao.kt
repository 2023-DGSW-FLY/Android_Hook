package com.innosync.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.innosync.data.local.base.BaseDao
import com.innosync.data.local.entity.token.FirebaseTokenEntity
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.data.util.TableEnv

@Dao
interface FirebaseTokenDao : BaseDao<FirebaseTokenEntity> {

    @Query("SELECT * FROM ${TableEnv.FIREBASE} WHERE idx = 0")
    suspend fun getToken(): FirebaseTokenEntity
}
