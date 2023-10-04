package com.innosync.data.local.entity.token

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.innosync.data.local.database.HookDataBase
import com.innosync.data.util.TableEnv

@Entity(
    tableName = TableEnv.TOKEN
)
data class TokenEntity(
    @PrimaryKey val idx: Int,
    val token: String,
    val refreshToken: String
) {
    constructor(token: String, refreshToken: String) : this(0, token, refreshToken)
}