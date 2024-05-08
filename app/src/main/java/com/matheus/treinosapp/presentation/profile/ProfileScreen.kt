package com.matheus.treinosapp.presentation.profile

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
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.home.WorkoutsCell
import com.matheus.treinosapp.presentation.utils.MainAppBar
import java.sql.Timestamp

@Composable
fun ProfileScreen(
    favoriteViewModel: FirestoreViewModel = hiltViewModel(),
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
                title = "Perfil",
                onLogoClick = {},
                onIconClick = {
                    onSignOut().let {
                        navController.navigate(AppGraph.auth.LOGIN)
                    }
                },
                isMainScreen = false
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {

            WorkoutsUser(userData = userData) {workouts ->
            if (workouts.isNotEmpty()) {
                WorkoutsCell(navController = navController,
                    workouts = workouts, "Meus treinos", true) {
                }
            } else {
                Text(text = "Você não tem treinos salvos!!")
            }
            }
        }
    }
    
    
}