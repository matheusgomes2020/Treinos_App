package com.matheus.treinosapp.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.matheus.treinosapp.navigation.RootGraph
import com.matheus.treinosapp.navigation.SplashGraph
import com.matheus.treinosapp.presentation.signIn.AuthViewModel
import com.matheus.treinosapp.presentation.splash.AnimatedSplashScreen

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