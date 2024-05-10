package com.matheus.treinosapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.common.SubtitleHeader
import com.matheus.treinosapp.ui.DpDimensions
@Composable
fun WorkoutsCell(
    navController: NavController,
    workouts: Workouts,
    text: String,
    isProfileScreen: Boolean,
    deleteWorkout: (idFirebase: String) -> Unit,
){

    var isProfile = if (isProfileScreen) "true" else "false"

    Column(
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)
    ) {
        SubtitleHeader(
            title = text,
            modifier = Modifier.fillMaxWidth(),
            isIconVisible = true,
            isSystemInDarkTheme = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(workouts) { workout ->
                WorkoutListItem(
                    workout = workout,
                    onItemClick = {
                        val routes = AppGraph.workouts_details.DETAILS + "/${workout.idFirebase}/${workout.name}/${workout.description}/${workout.timestamp}/${workout.username}/${isProfile}"
                        navController.navigate(routes)
                    },
                    deleteWorkout = {}
                )
            }
        }
    }
}