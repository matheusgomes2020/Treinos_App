package com.matheus.treinosapp.presentation.workout_detail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.treinosapp.domain.model.Exercise
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.repository.Exercises
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel
import com.matheus.treinosapp.presentation.common.ProgressBar

@Composable
fun WorkoutExercises(
    workoutId: String,
    viewModel: FirestoreViewModel = hiltViewModel(),
    exercisesContent: @Composable (exercises: Exercises ) -> Unit
) {

    var listOfExercises = emptyList<Exercise>()
    when(val exercisesResponse = viewModel.exercisesResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> exercisesResponse.data.let { exercises ->
            listOfExercises = exercises!!
            listOfExercises = exercises.filter { exercise ->
                exercise.idWorkout == workoutId
            }
            exercisesContent(listOfExercises)
        }
        is Response.Failure -> print(exercisesResponse.e)
    }

}

@Composable
fun ExercisesUser(
    idWorkout: String,
    viewModel: FirestoreViewModel = hiltViewModel(),
    exercisesContent: @Composable (exercises: Exercises ) -> Unit
) {

    var listOfExercises = emptyList<Exercise>()
    when(val exercisesResponse = viewModel.exercisesResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> exercisesResponse.data.let { exercises ->
            listOfExercises = exercises!!
            listOfExercises = exercises.filter { exercise ->
                exercise.idWorkout == idWorkout
            }
            exercisesContent(listOfExercises)
            Log.d("FFFFIRRE", "FOI???: $listOfExercises")
        }
        is Response.Failure -> print(exercisesResponse.e)
    }

}