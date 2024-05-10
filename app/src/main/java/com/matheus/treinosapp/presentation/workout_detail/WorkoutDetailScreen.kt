package com.matheus.treinosapp.presentation.workout_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.common.AppBarWithBack
import com.matheus.treinosapp.presentation.common.SubtitleHeader
import com.matheus.treinosapp.presentation.workout_detail.components.ExercisesCell
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.OrangeApp

@Composable
fun WorkoutDetailScreen(
    navController: NavController,
    viewModel: WorkoutDetailViewModel = hiltViewModel(),
    isUserWorkout: Boolean
) {

    val state = viewModel.state.value

    Scaffold(
        topBar = {
            AppBarWithBack(
                title = state.workout!!.name,
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

            Column(
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)) {

                SubtitleHeader(
                    title = "Descrição",
                    modifier = Modifier.fillMaxWidth(),
                    isIconVisible = false,
                    isSystemInDarkTheme = true,
                    onClick = {

                    }
                )
                Text(text = state.workout!!.description)
            }
            WorkoutExercises(workoutId = state.workout!!.idFirebase) {exercises ->
                ExercisesCell(navController = navController,
                    exercises = exercises) {
                }
            }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            Column(

                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)
            ) {
                if (isUserWorkout)
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            //contentColor = Color.Green,
                            containerColor = OrangeApp
                        ),
                        onClick = {
                            navController.navigate(AppGraph.add_exercises.ADD + "/${state.workout.idFirebase}/${state.workout.username}")
                        }) {
                        Text(text = "Adicionar exercício")
                    }
            }
        }
    }
}