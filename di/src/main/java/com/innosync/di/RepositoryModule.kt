package com.innosync.di

import com.innosync.data.repository.ExampleGetGetRepositoryImpl
import com.innosync.domain.repository.ExampleGetRepository
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
}