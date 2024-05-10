package com.matheus.treinosapp.presentation.workout_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.matheus.treinosapp.utils.Constants
import com.matheus.treinosapp.domain.model.Workout
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WorkoutDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
    ): ViewModel() {
    private val _state = mutableStateOf(WorkoutDetailState())
    val state: State<WorkoutDetailState> = _state

    private var workout =  Workout()
    init {

        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_ID)?.let { id -> workout.idFirebase = id }
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_NAME)?.let { name -> workout.name = name }
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_DESCRIPTION)?.let { description -> workout.description = description }
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_TIMESTAMP)?.let { timestamp -> workout.timestamp = timestamp }
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_USERNAME)?.let { username -> workout.username = username }

        getWorkout(workout = workout)

    }

    private fun getWorkout( workout: Workout ) {
        _state.value = WorkoutDetailState(workout = workout)
    }
    }