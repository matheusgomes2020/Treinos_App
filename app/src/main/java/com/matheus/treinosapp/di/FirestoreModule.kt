package com.matheus.treinosapp.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.matheus.treinosapp.common.Constants.WORKOUTS
import com.matheus.treinosapp.data.repository.WorkoutsRepositoryImpl
import com.matheus.treinosapp.domain.repository.WorkoutsRepository
import com.matheus.treinosapp.domain.use_case.workouts.AddWorkout
import com.matheus.treinosapp.domain.use_case.workouts.DeleteWorkout
import com.matheus.treinosapp.domain.use_case.workouts.GetWorkouts
import com.matheus.treinosapp.domain.use_case.workouts.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn( SingletonComponent::class )
object FirestoreModule {

    @Provides
    fun provideWorkoutsRef() = Firebase.firestore.collection(WORKOUTS)

    @Provides
    fun provideWorkoutsRepository(
        workoutsRef: CollectionReference
    ): WorkoutsRepository = WorkoutsRepositoryImpl(workoutsRef)

    @Provides
    fun provideUseCases(
        repo: WorkoutsRepository,
    ) = UseCases(
        getWorkouts = GetWorkouts(repo),
        addWorkout = AddWorkout(repo),
        deleteWorkout = DeleteWorkout(repo)
    )

}