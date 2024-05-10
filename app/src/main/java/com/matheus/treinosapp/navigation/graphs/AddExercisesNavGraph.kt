package com.matheus.treinosapp.navigation.graphs

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.add_exercises.AddExercisesScreen

fun NavGraphBuilder.addExercisesNavGraph(navController: NavController, userData: UserData? ){
    navigation(
        route = AppGraph.add_exercises.ROOT,
        startDestination = AppGraph.add_exercises.ADD
    ) {
        composable(route = AppGraph.add_exercises.ADD + "/{workoutId}/{username}",

            arguments = listOf(
                navArgument( "workoutId" ) {
                    type = NavType.StringType
                },
                navArgument( "username" ) {
                    type = NavType.StringType
                }
            )
        ) {navBackStackEntry ->

            navBackStackEntry.arguments?.getString("workoutId").let {
                Log.d("XUXU", "workoutsDetailsNavGraph: ${navBackStackEntry.arguments}")
                AddExercisesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData)
            }
        }
    }

}