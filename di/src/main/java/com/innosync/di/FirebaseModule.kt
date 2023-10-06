package com.innosync.di

import com.google.firebase.firestore.FirebaseFirestore
import com.innosync.data.repository.FirebaseRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Singleton
    @Provides
    fun providesFirebase(): FirebaseFirestore =
        FirebaseFirestore.getInstance()


}