package com.matheus.treinosapp.presentation.home.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.common.ProgressBar
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel

@Composable
fun Workouts(
    userData: UserData?,
    viewModel: FirestoreViewModel = hiltViewModel(),
    workoutsContent: @Composable ( workouts: Workouts ) -> Unit
) {

    var listOfWorkouts = emptyList<Workout>()
    when(val workoutsResponse = viewModel.workoutsResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> workoutsResponse.data.let { workouts ->
            listOfWorkouts = workouts!!
            workoutsContent(listOfWorkouts)
        }
        is Response.Failure -> print(workoutsResponse.e)
    }

}