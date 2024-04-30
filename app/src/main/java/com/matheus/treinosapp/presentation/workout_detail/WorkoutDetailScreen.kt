package com.matheus.treinosapp.presentation.workout_detail

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.AppBarWithBack
import com.matheus.treinosapp.presentation.favorites.Exercises
import com.matheus.treinosapp.presentation.home.ExercisesCell

@Composable
fun WorkoutDetailScreen(
    navController: NavController,
    viewModel: WorkoutDetailViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Workout",
                backIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { it
        Column(
            modifier = Modifier.padding(it)
        ) {
            Text(text = "ID")
            Text(text = state.workout!!.idFirebase)

            Text(text = "NAME")
            Text(text = state.workout!!.name)

            Text(text = "DESCRIPTION")
            Text(text = state.workout!!.description)

            Text(text = "TIMESTAMP")
            Text(text = state.workout!!.timestamp)

            Text(text = "USERNAME")
            Text(text = state.workout!!.username)


            WorkoutExercises(workoutId = state.workout!!.idFirebase) {exercises ->
                Log.d("ACABOU", "Antes???: $exercises")
//            if (!workouts.isNullOrEmpty()) {
                ExercisesCell(navController = navController,
                    exercises = exercises) {
                    Log.d("ACABOU", "WorkoutExercises???: $exercises")
                    //  }
                }
            }

            Button(onClick = {
                navController.navigate(AppGraph.add_exercises.ADD + "/${state.workout.idFirebase}/${state.workout.username}")
            }) {
                Text(text = "Adicionar exercício")
            }
        }
    }
}