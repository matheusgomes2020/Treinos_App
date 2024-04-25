package com.matheus.treinosapp.presentation.favorites

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.repository.AddWorkoutResponse
import com.matheus.treinosapp.domain.repository.DeleteWorkoutResponse
import com.matheus.treinosapp.domain.repository.WorkoutsResponse
import com.matheus.treinosapp.domain.use_case.workouts.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    var workoutsResponse by mutableStateOf<WorkoutsResponse>(Response.Loading)
        private set
    var addWorkoutResponse by mutableStateOf<AddWorkoutResponse>(Response.Success(false))
        private set
    var deleteWorkoutResponse by mutableStateOf<DeleteWorkoutResponse>(Response.Success(false))
        private set

    init {
        getWorkouts()
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
}

