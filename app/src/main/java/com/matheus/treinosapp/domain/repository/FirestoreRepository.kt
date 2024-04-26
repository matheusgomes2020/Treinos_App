package com.matheus.treinosapp.domain.repository


import com.matheus.treinosapp.domain.model.Exercise
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.model.Response
import kotlinx.coroutines.flow.Flow


typealias Workouts = List<Workout>
typealias WorkoutsResponse = Response<Workouts>
typealias AddWorkoutResponse = Response<Boolean>
typealias DeleteWorkoutResponse = Response<Boolean>

typealias Exercises = List<Exercise>
typealias ExercisesResponse = Response<Exercises>
typealias AddExerciseResponse = Response<Boolean>
typealias DeleteExerciseResponse = Response<Boolean>

interface FirestoreRepository {

    //fun getWorkouts(): Flow<Response<List<Workout>>>
    fun getWorkouts(): Flow<WorkoutsResponse>

    suspend fun addWorkout(id: String,name: String, description: String, timestamp: String, userId: String, userName: String) : AddWorkoutResponse

    suspend fun deleteWorkout(id: String) : DeleteWorkoutResponse

    fun getExercises(): Flow<ExercisesResponse>

    suspend fun addExercise(id: String,name: String, imageUrl: String, observations: String, userId: String, idWorkout: String) : AddExerciseResponse

    suspend fun deleteExercise(id: String) : DeleteExerciseResponse

}