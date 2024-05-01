package com.matheus.treinosapp.presentation.profile

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.repository.AddExerciseResponse
import com.matheus.treinosapp.domain.repository.AddWorkoutResponse
import com.matheus.treinosapp.domain.repository.DeleteExerciseResponse
import com.matheus.treinosapp.domain.repository.DeleteWorkoutResponse
import com.matheus.treinosapp.domain.repository.ExercisesResponse
import com.matheus.treinosapp.domain.repository.WorkoutsResponse
import com.matheus.treinosapp.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirestoreViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var workoutsResponse by mutableStateOf<WorkoutsResponse>(Response.Loading)
        private set
    var addWorkoutResponse by mutableStateOf<AddWorkoutResponse>(Response.Success(false))
        private set
    var deleteWorkoutResponse by mutableStateOf<DeleteWorkoutResponse>(Response.Success(false))
        private set

    var exercisesResponse by mutableStateOf<ExercisesResponse>(Response.Loading)
        private set
    var addExerciseResponse by mutableStateOf<AddExerciseResponse>(Response.Success(false))
        private set
    var deleteExerciseResponse by mutableStateOf<DeleteExerciseResponse>(Response.Success(false))
        private set

    init {
        getWorkouts()
        getExercises()
    }

    private fun getWorkouts() = viewModelScope.launch {
        useCases.getWorkouts().collect { response ->
            workoutsResponse = response
            Log.d("FFFFIRRE", "getmovies: " + response)
        }
    }

    fun addWorkout(id: String,
                 name: String,
                 description: String,
                 timestamp: String,
                 userId: String,
                 userName: String
    ) = viewModelScope.launch {
        Log.d("FFFFIRRE", "Add:  $id, $name, $timestamp, $userId")
        addWorkoutResponse = Response.Loading
        addWorkoutResponse = useCases.addWorkout(id, name, description, timestamp, userId, userName)
        Log.d("FFFFIRRE", "Add: " + addWorkoutResponse.toString())

    }

    fun deleteWorkout(idFirebase: String) = viewModelScope.launch {
        deleteWorkoutResponse = Response.Loading
        deleteWorkoutResponse = useCases.deleteWorkout( idFirebase )
    }

    private fun getExercises() = viewModelScope.launch {
        useCases.getExercises().collect { response ->
            exercisesResponse = response
            Log.d("FFFFIRRE", "getmovies: " + response)
        }
    }

    fun addExercise(id: String,
                   name: String,
                    imageUrl: String,
                    observations: String,
                    userId: String,
                    idWorkout: String
    ) = viewModelScope.launch {
        Log.d("FFFFIRRE", "Add:  $id, $name, $imageUrl, $userId")
        addExerciseResponse = Response.Loading
        addExerciseResponse = useCases.addExercise(id, name, imageUrl, observations, userId, idWorkout)
        Log.d("FFFFIRRE", "Add: " + addExerciseResponse.toString())

    }

    fun deleteExercise(idFirebase: String) = viewModelScope.launch {
        deleteExerciseResponse = Response.Loading
        deleteExerciseResponse = useCases.deleteExercise( idFirebase )
    }

}

