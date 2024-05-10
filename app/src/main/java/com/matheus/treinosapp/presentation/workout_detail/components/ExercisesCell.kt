package com.matheus.treinosapp.presentation.workout_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.matheus.treinosapp.domain.repository.Exercises
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.common.SubtitleHeader

import com.matheus.treinosapp.ui.DpDimensions
@Composable
fun ExercisesCell(
    navController: NavController,
    exercises: Exercises,
    deleteExercise: (idFirebase: String) -> Unit
){


    Column(
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)
    ) {
        SubtitleHeader(
            title = "Exercícios",
            modifier = Modifier.fillMaxWidth(),
            isIconVisible = true,
            isSystemInDarkTheme = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        if (!exercises.isNullOrEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(exercises) { exercise ->
                    ExerciseListItem(
                        exercise = exercise,
                        onItemClick = {
                            navController.navigate(AppGraph.exercises_details.DETAILS+ "/${exercise.idFirebase}/${exercise.name}/${exercise.observations}/${exercise.imageUrl.toString()}" )
                                   // "/${exercise.name}/${exercise.userId}/${exercise.observations}/${exercise.imageUrl}")
                        },
                        deleteExercise = {}
                    )
                }
            }
        } else {
            Text(text = "Este treino não tem exercícios!!!")
        }
    }
}