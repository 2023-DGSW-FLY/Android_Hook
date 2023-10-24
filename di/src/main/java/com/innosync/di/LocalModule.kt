package com.innosync.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.innosync.data.local.dao.FirebaseTokenDao
import com.innosync.data.local.dao.TokenDao
import com.innosync.data.local.database.HookDataBase
import com.innosync.data.util.TableEnv
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideHookDataBase(
        @ApplicationContext context: Context
    ): HookDataBase = Room
        .databaseBuilder(
            context,
            HookDataBase::class.java,
            TableEnv.DATABASE
        )
        .build()

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context
        .getSharedPreferences("hook_prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideTokenDao(
        hookDataBase: HookDataBase
    ): TokenDao = hookDataBase.tokenDao()

    @Provides
    @Singleton
    fun provideFirebaseTokenDao(
        hookDataBase: HookDataBase
    ): FirebaseTokenDao = hookDataBase.firebaseTokenDao()
}