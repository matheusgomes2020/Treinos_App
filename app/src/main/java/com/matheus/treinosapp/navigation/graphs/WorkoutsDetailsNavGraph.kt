package com.matheus.treinosapp.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.workout_detail.WorkoutDetailScreen

fun NavGraphBuilder.workoutsDetailsNavGraph(navController: NavController, userData: UserData? ){
    navigation(
        route = AppGraph.workouts_details.ROOT,
        startDestination = AppGraph.workouts_details.DETAILS
    ) {
        composable(route = AppGraph.workouts_details.DETAILS + "/{workoutId}/{name}/{description}/{timestamp}/{username}/{isWorkoutUser}",

            arguments = listOf(
                navArgument( "workoutId" ) {
                    type = NavType.StringType
                },
                navArgument( "name" ) {
                    type = NavType.StringType
                }
                ,
                navArgument( "description" ) {
                    type = NavType.StringType
                }
                ,
                navArgument( "timestamp" ) {
                    type = NavType.StringType
                }
                ,
                navArgument( "username" ) {
                    type = NavType.StringType
                }
                ,
                navArgument( "isWorkoutUser" ) {
                    type = NavType.StringType
                }
            )
        ) {navBackStackEntry ->

            navBackStackEntry.arguments?.getString("isWorkoutUser").let { isW ->
                if (isW == "true")
                    WorkoutDetailScreen(navController = navController, isUserWorkout = true)
                else
                    WorkoutDetailScreen(navController = navController, isUserWorkout = false)
            }
        }
        addExercisesNavGraph( navController = navController, userData = userData )
        exercisesDetailsNavGraph( navController = navController)
    }

}