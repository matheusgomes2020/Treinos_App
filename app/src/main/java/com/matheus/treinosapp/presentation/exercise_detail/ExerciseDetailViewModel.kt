package com.matheus.treinosapp.presentation.exercise_detail

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
class ExerciseDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = mutableStateOf(ExerciseDetailState())
    val state: State<ExerciseDetailState> = _state

    private var exercise = Exercise()
    init {

        savedStateHandle.get<String>(Constants.PARAM_EXERCISE_ID)?.let { id -> exercise.idFirebase = id
            Log.d("XUXUA", "view model: $id")}

        savedStateHandle.get<String>(Constants.PARAM_EXERCISE_NAME)?.let { name -> exercise.name = name }
//        savedStateHandle.get<String>(Constants.PARAM_EXERCISE_USERNAME)?.let { username -> exercise.userId = username }
        savedStateHandle.get<String>(Constants.PARAM_EXERCISE_OBSERVATIONS)?.let { observations -> exercise.observations = observations }
        savedStateHandle.get<String>(Constants.PARAM_EXERCISE_IMAGE_URL)?.let { imageUrl -> exercise.imageUrl = imageUrl }

        getExercise(exercise = exercise)
    }

    private fun getExercise( exercise: Exercise ) {
        _state.value = ExerciseDetailState(exercise = exercise)
    }
}