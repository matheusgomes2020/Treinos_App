package com.matheus.treinosapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.matheus.treinosapp.data.repository.ImageRepositoryImpl
import com.matheus.treinosapp.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class StorageModule {

    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    fun provideImageRepository(
        storage: FirebaseStorage,
        db: FirebaseFirestore
    ): ImageRepository = ImageRepositoryImpl(
        storage = storage,
        db= db
    )

}