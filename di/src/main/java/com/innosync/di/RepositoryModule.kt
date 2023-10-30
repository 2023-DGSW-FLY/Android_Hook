package com.innosync.di

import com.innosync.data.repository.CongressRepositoryImpl
import com.innosync.data.repository.ExampleGetGetRepositoryImpl
import com.innosync.data.repository.FirebaseRepositoryImpl
import com.innosync.data.repository.JobOpeningRepositoryImpl
import com.innosync.data.repository.JobSearchRepositoryImpl
import com.innosync.data.repository.AuthRepositoryImpl
import com.innosync.data.repository.ChatRepositoryImpl
import com.innosync.data.repository.FirebaseTokenRepositoryImpl
import com.innosync.data.repository.AlarmRepositoryImpl
import com.innosync.data.repository.TokenRepositoryImpl
import com.innosync.data.repository.UserRepositoryImpl
import com.innosync.domain.repository.CongressRepository
import com.innosync.domain.repository.ExampleGetRepository
import com.innosync.domain.repository.FirebaseRepository
import com.innosync.domain.repository.JobOpeningRepository
import com.innosync.domain.repository.JobSearchRepository
import com.innosync.domain.repository.AuthRepository
import com.innosync.domain.repository.ChatRepository
import com.innosync.domain.repository.FirebaseTokenRepository
import com.innosync.domain.repository.AlarmRepository
import com.innosync.domain.repository.TokenRepository
import com.innosync.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun providesExampleRepository(
        exampleGetRepositoryImpl: ExampleGetGetRepositoryImpl
    ): ExampleGetRepository

    @Singleton
    @Binds
    abstract fun providesFirebaseRepository(
        firebaseRepositoryImpl: FirebaseRepositoryImpl
    ): FirebaseRepository

    @Singleton
    @Binds
    abstract fun providesCongressRepository(
        congressRepositoryImpl: CongressRepositoryImpl
    ): CongressRepository

    @Singleton
    @Binds
    abstract fun provideJobOpeningRepository(
        jobOpeningRepositoryImpl: JobOpeningRepositoryImpl
    ): JobOpeningRepository

    @Singleton
    @Binds
    abstract fun provideJobSearchRepository(
        jobSearchRepositoryImpl: JobSearchRepositoryImpl
    ): JobSearchRepository

    @Singleton
    @Binds
    abstract fun providesLoginRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun providesTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl
    ): TokenRepository

    @Singleton
    @Binds
    abstract fun providesUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Singleton
    @Binds
    abstract fun providesChatRepository(
        chatRepositoryImpl: ChatRepositoryImpl
    ): ChatRepository

    @Singleton
    @Binds
    abstract fun providesFirebaseTokenRepository(
        firebaseTokenRepositoryImpl: FirebaseTokenRepositoryImpl
    ): FirebaseTokenRepository

    @Singleton
    @Binds
    abstract fun providesSharedPreferencesRepository(
        alarmRepositoryImpl: AlarmRepositoryImpl
    ): AlarmRepository
}