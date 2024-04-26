package com.matheus.treinosapp.presentation.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.treinosapp.R
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.MainAppBar
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.home.ExercisesCell
import com.matheus.treinosapp.presentation.home.WorkoutsCell
import java.sql.Timestamp

@Composable
fun FavoritesScreen(
    favoriteViewModel: FavoritesViewModel = hiltViewModel(),
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    onSignOut: () -> Unit
){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else Color.DarkGray,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            MainAppBar(
                icon1 = R.drawable.logout,
                title = "Treinos",
                imageUrl = "",
                onLogoClick = {},
                onSearchClick = {
                    onSignOut().let {
                        navController.navigate(AppGraph.auth.LOGIN)
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {
            Button(onClick = {
                val currentTimeMillis = System.currentTimeMillis()
                val timeStamp = Timestamp(currentTimeMillis)
                favoriteViewModel.addWorkout(
                    "43434234",
                    "3",
                    "Treino de costas",
                    timeStamp.toString(),
                    userId = userData!!.userId,
                    userName = userData.username!!
                )
            }) {
                Text(text = "Adicionar")
            }

            Button(onClick = {
                favoriteViewModel.addExercise(
                    id = "0012345678910",
                    name = "7",
                    imageUrl = "",
                    observations = "7 repetições de 15",
                    userId = userData!!.userId,
                    idWorkout = "para depois"

                )
            }) {
                Text(text = "Adicionar exercício")
            }


            WorkoutsUser(userData = userData) {workouts ->
//            if (!workouts.isNullOrEmpty()) {
                WorkoutsCell(navController = navController,
                    workouts = workouts) {

                    //  }
                }
            }

            Exercises(userData = userData) {exercises ->
//            if (!workouts.isNullOrEmpty()) {
                ExercisesCell(navController = navController,
                    exercises = exercises) {

                    //  }
                }
            }
        }
    }
    
    
}