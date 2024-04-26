package com.matheus.treinosapp.navigation.graphs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.matheus.treinosapp.navigation.AuthGraph
import com.matheus.treinosapp.navigation.HomeGraph
import com.matheus.treinosapp.navigation.RootGraph
import com.matheus.treinosapp.navigation.SplashGraph
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.add.AddScreen
import com.matheus.treinosapp.presentation.home.HomeScreen
import com.matheus.treinosapp.presentation.main.MainScreen
import com.matheus.treinosapp.presentation.favorites.FavoritesScreen
import com.matheus.treinosapp.presentation.signIn.AuthViewModel
import com.matheus.treinosapp.presentation.signIn.LoginScreen
import com.matheus.treinosapp.presentation.signUp.SignupScreen
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
            FavoritesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData, onSignOut = onSignOut)
        }
        authNavGraph( navController = navController, viewModel = viewModel )

    }
}