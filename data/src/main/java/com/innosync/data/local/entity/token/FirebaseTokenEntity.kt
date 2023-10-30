package com.innosync.data.local.entity.token

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.innosync.data.util.TableEnv

@Entity(
    tableName = TableEnv.FIREBASE
)
data class FirebaseTokenEntity(
    @PrimaryKey val idx: Int,
    val token: String,
) {
    constructor(token: String) : this(0, token)
}