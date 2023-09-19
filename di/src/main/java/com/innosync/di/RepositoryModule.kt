package com.innosync.di

import com.innosync.data.repository.CongressRepositoryImpl
import com.innosync.data.repository.ExampleGetGetRepositoryImpl
import com.innosync.data.repository.FirebaseRepositoryImpl
import com.innosync.domain.repository.CongressRepository
import com.innosync.domain.repository.ExampleGetRepository
import com.innosync.domain.repository.FirebaseRepository
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
}