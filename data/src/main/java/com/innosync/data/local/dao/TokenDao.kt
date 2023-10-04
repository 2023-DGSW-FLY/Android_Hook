package com.innosync.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.innosync.data.local.base.BaseDao
import com.innosync.data.local.entity.token.TokenEntity
import com.innosync.data.util.TableEnv

@Dao
interface TokenDao : BaseDao<TokenEntity> {

    @Query("SELECT * FROM ${TableEnv.TOKEN} WHERE idx = 0")
    suspend fun getToken(): TokenEntity

    @Query("DELETE FROM ${TableEnv.TOKEN}")
    suspend fun deleteToken()
}
