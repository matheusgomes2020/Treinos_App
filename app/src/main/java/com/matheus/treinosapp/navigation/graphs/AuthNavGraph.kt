package com.matheus.treinosapp.navigation.graphs


import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AuthGraph
import com.matheus.treinosapp.presentation.main.MainScreen
import com.matheus.treinosapp.presentation.signIn.AuthViewModel
import com.matheus.treinosapp.presentation.signIn.LoginScreen
import com.matheus.treinosapp.presentation.signUp.SignupScreen

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