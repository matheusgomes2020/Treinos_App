package com.matheus.treinosapp.presentation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.matheus.treinosapp.navigation.graphs.HomeNavGraph
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.main.components.BottomNavBar
import com.matheus.treinosapp.presentation.main.components.Screen
import com.matheus.treinosapp.presentation.signIn.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    //userData: UserData?,
               authViewModel: AuthViewModel,
               onSignOut: () -> Unit) {

    var bottomBarVisible by rememberSaveable {
        mutableStateOf(false)
    }
    val useDarkIcons = !isSystemInDarkTheme()

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    var userData: UserData = UserData("","","", "")

    authViewModel.currentUser.let {
        if (it != null) {
            userData = UserData(
                userId = it.uid,
                email = it.email!!,
                username = it.displayName,
                profilePictureUrl = ""
            )
        }
    }

    bottomBarVisible = when (navBackStackEntry?.destination?.route) {
        Screen.Home.route -> true
        Screen.Add.route -> true
        Screen.Favorites.route -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                visible = bottomBarVisible,
                isSystemInDarkMode = isSystemInDarkTheme()
            )
        }
    ) {

        HomeNavGraph(navController = navController, userData = userData, viewModel = authViewModel, onSignOut = onSignOut)

    }

}
