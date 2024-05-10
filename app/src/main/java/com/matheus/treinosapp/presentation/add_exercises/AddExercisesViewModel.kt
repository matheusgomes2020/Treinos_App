package com.matheus.treinosapp.presentation.add_exercises

import android.util.Log

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.matheus.treinosapp.utils.Constants
import com.matheus.treinosapp.domain.model.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExercisesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(AddExerciseState())
    val state: State<AddExerciseState> = _state

    private var exercise = Exercise()
    init {
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_ID)?.let { id -> exercise.idWorkout = id }
        savedStateHandle.get<String>(Constants.PARAM_WORKOUT_USERNAME)?.let { userName -> exercise.userId = userName }
//      savedStateHandle.get<String>(Constants.PARAM_EXERCISE_IMAGE_URL)?.let { description -> exercise.imageUrl = description }

        getParams(exercise.idWorkout, exercise.userId)

    }

    private fun getParams( idWorkout: String, userName: String ) {
        _state.value = AddExerciseState(idWorkout, userName)
    }
}