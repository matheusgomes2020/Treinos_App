package com.matheus.treinosapp.domain.repository


import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.sql.Timestamp


typealias Workouts = List<Workout>
typealias WorkoutsResponse = Response<Workouts>
typealias AddWorkoutResponse = Response<Boolean>
typealias DeleteWorkoutResponse = Response<Boolean>

interface WorkoutsRepository {

    //fun getWorkouts(): Flow<Response<List<Workout>>>
    fun getWorkouts(): Flow<WorkoutsResponse>

    suspend fun addWorkout(id: Int,name: Int, description: String, timestamp: Timestamp, userId: String) : AddWorkoutResponse

    suspend fun deleteWorkout(id: String) : DeleteWorkoutResponse

}