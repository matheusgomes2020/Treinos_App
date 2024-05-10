package com.matheus.treinosapp.navigation.graphs

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.exercise_detail.ExerciseDetailScreen

fun NavGraphBuilder.exercisesDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.exercises_details.ROOT,
        startDestination = AppGraph.exercises_details.DETAILS
    ) {
        composable(route = AppGraph.exercises_details.DETAILS + "/{exerciseId}/{exerciseName}/{exerciseObservations}/{exerciseImageUrl}",
            arguments = listOf(
                navArgument( "exerciseId" ) {
                    type = NavType.StringType
                },
                navArgument( "exerciseName" ) {
                    type = NavType.StringType
                },
                navArgument( "exerciseObservations" ) {
                    type = NavType.StringType
                }
                ,
                navArgument( "exerciseImageUrl" ) {
                    type = NavType.StringType
                }
            )
        ) {navBackStackEntry ->
            navBackStackEntry.arguments?.getString("exerciseId").let {
                ExerciseDetailScreen(navController)
            }
        }
    }

}