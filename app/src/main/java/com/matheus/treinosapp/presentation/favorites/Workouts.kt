package com.matheus.treinosapp.presentation.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.utils.ProgressBar

@Composable
fun Workouts(
    userData: UserData?,
    viewModel: FavoritesViewModel = hiltViewModel(),
    workoutsContent: @Composable ( workouts: Workouts ) -> Unit
) {

    var listOfWorkouts = emptyList<Workout>()
    when(val moviesResponse = viewModel.workoutsResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> moviesResponse.data.let { workouts ->
            listOfWorkouts = workouts
            listOfWorkouts = workouts.filter { workout ->
                workout.userId == userData?.userId
            }
            workoutsContent(listOfWorkouts)
        }
        is Response.Failure -> print(moviesResponse.e)
    }

}