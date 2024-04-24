package com.matheus.treinosapp.di

import com.google.firebase.auth.FirebaseAuth
import com.matheus.treinosapp.data.repository.AuthRepositoryImpl
import com.matheus.treinosapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl


}