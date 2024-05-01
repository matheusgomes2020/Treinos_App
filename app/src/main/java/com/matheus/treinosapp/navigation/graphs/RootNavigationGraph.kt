package com.matheus.treinosapp.navigation.graphs

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.navigation.AuthGraph
import com.matheus.treinosapp.navigation.HomeGraph
import com.matheus.treinosapp.navigation.RootGraph
import com.matheus.treinosapp.navigation.SplashGraph
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.add.AddScreen
import com.matheus.treinosapp.presentation.add_exercises.AddExercisesScreen
import com.matheus.treinosapp.presentation.exercise_detail.ExerciseDetailScreen
import com.matheus.treinosapp.presentation.home.HomeScreen
import com.matheus.treinosapp.presentation.main.MainScreen
import com.matheus.treinosapp.presentation.profile.ProfileScreen
import com.matheus.treinosapp.presentation.signIn.AuthViewModel
import com.matheus.treinosapp.presentation.signIn.LoginScreen
import com.matheus.treinosapp.presentation.signUp.SignupScreen
import com.matheus.treinosapp.presentation.splash.AnimatedSplashScreen
import com.matheus.treinosapp.presentation.workout_detail.WorkoutDetailScreen

@Composable
fun RootNavigationGraph(navController: NavHostController, viewModel: AuthViewModel) {

    NavHost(
        navController = navController,
        route = RootGraph.ROOT,
        startDestination = SplashGraph.ROOT
    ) {
        composable(route = SplashGraph.ROOT) {
            AnimatedSplashScreen(navController = navController)
        }
        authNavGraph(navController = navController, viewModel = viewModel)
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController, viewModel: AuthViewModel) {
    navigation(
        route = AuthGraph.ROOT,
        startDestination = AuthGraph.LOGIN
    ) {
        composable(route = AuthGraph.LOGIN) {
            LoginScreen(viewModel = viewModel , navController = navController)
        }
        composable(route = AuthGraph.SIGN_UP) {
            SignupScreen(viewModel = viewModel, navController = navController )
        }
        composable(route = AuthGraph.FORGOT_PASSWORD) {
            MainScreen(
                authViewModel = viewModel
            ) {
                viewModel?.logout()
            }
        }

    }
}

@Composable
fun HomeNavGraph(navController: NavHostController, userData: UserData?, viewModel: AuthViewModel,
                 onSignOut: () -> Unit) {


    NavHost(
        navController = navController,
        route = HomeGraph.ROOT,
        startDestination = HomeGraph.HOME
    ) {
        composable(route = HomeGraph.HOME) {
            HomeScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData)
        }
        composable(route = HomeGraph.ADD) {
            AddScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData)
        }
        composable(route = HomeGraph.FAVORITES) {
            ProfileScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData, onSignOut = onSignOut)
        }
        authNavGraph( navController = navController, viewModel = viewModel )

        workoutsDetailsNavGraph(navController, userData)


    }
}

fun NavGraphBuilder.workoutsDetailsNavGraph(navController: NavController, userData: UserData? ){
    navigation(
        route = AppGraph.workouts_details.ROOT,
        startDestination = AppGraph.workouts_details.DETAILS
    ) {
        composable(route = AppGraph.workouts_details.DETAILS + "/{workoutId}/{name}/{description}/{timestamp}/{username}",

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
            )
        ) {navBackStackEntry ->

            navBackStackEntry.arguments?.getString("workoutId").let {
                Log.d("XUXU", "workoutsDetailsNavGraph: ${navBackStackEntry.destination}")
               WorkoutDetailScreen(navController)
            }
        }
        addExercisesNavGraph( navController = navController, userData = userData )
        exercisesDetailsNavGraph( navController = navController)
    }

}

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

fun NavGraphBuilder.exercisesDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.exercises_details.ROOT,
        startDestination = AppGraph.exercises_details.DETAILS
    ) {
        composable(route = AppGraph.exercises_details.DETAILS + "/{exerciseId}/{exerciseName}/{exerciseObservations}",
                //"/{exerciseUsername}/{exerciseObservations}/{exerciseImageUrl}",

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
//                ,
//                navArgument( "exerciseImageUrl" ) {
//                    type = NavType.StringType
//                }
            )
        ) {navBackStackEntry ->

            navBackStackEntry.arguments?.getString("exerciseId").let {
                Log.d("XUXUA", "workoutsDetailsNavGraph: ${navBackStackEntry.arguments}")
                ExerciseDetailScreen(navController)
            }
        }
    }

}