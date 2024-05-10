package com.matheus.treinosapp.navigation.graphs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.matheus.treinosapp.navigation.HomeGraph
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.add.AddScreen
import com.matheus.treinosapp.presentation.home.HomeScreen
import com.matheus.treinosapp.presentation.profile.ProfileScreen
import com.matheus.treinosapp.presentation.signIn.AuthViewModel

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