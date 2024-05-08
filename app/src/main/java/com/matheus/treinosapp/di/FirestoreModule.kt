package com.matheus.treinosapp.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.matheus.treinosapp.common.Constants.EXERCISES
import com.matheus.treinosapp.common.Constants.IMAGES
import com.matheus.treinosapp.common.Constants.WORKOUTS
import com.matheus.treinosapp.data.repository.FirestoreRepositoryImpl
import com.matheus.treinosapp.data.repository.ImageRepositoryImpl
import com.matheus.treinosapp.domain.repository.FirestoreRepository
import com.matheus.treinosapp.domain.repository.ImageRepository
import com.matheus.treinosapp.domain.use_case.workouts.AddWorkout
import com.matheus.treinosapp.domain.use_case.workouts.DeleteWorkout
import com.matheus.treinosapp.domain.use_case.workouts.GetWorkouts
import com.matheus.treinosapp.domain.use_case.UseCases
import com.matheus.treinosapp.domain.use_case.exercises.AddExercise
import com.matheus.treinosapp.domain.use_case.exercises.DeleteExercise
import com.matheus.treinosapp.domain.use_case.exercises.GetExercises
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class )
object FirestoreModule {

    @Provides
    @Singleton
    @Named("workouts")
    fun provideWorkoutsRef() = Firebase.firestore.collection(WORKOUTS)

    @Provides
    @Singleton
    @Named("exercises")
    fun provideExercisesRef() = Firebase.firestore.collection(EXERCISES)

    @Provides
    @Singleton
    @Named("images")
    fun provideImagesRef() = Firebase.firestore.collection(IMAGES)

    @Provides
    fun provideWorkoutsRepository(
        @Singleton
        @Named("workouts")
        workoutsRef: CollectionReference,
        @Named("exercises")
        exercisesRef: CollectionReference,
    ): FirestoreRepository = FirestoreRepositoryImpl(workoutsRef, exercisesRef)

    @Provides
    fun provideUseCases(
        repo: FirestoreRepository,
    ) = UseCases(
        getWorkouts = GetWorkouts(repo),
        addWorkout = AddWorkout(repo),
        deleteWorkout = DeleteWorkout(repo),

        getExercises = GetExercises(repo),
        addExercise = AddExercise(repo),
        deleteExercise = DeleteExercise(repo)
    )

    @Provides
    fun provideFirebaseStorage() = Firebase.storage

    @Provides
    fun provideImageRepository(
        storage: FirebaseStorage,
        @Singleton
        @Named("images")
        imagesRef: CollectionReference
    ): ImageRepository = ImageRepositoryImpl(
        storage = storage,
        imagesRef = imagesRef
    )

}