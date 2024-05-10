package com.matheus.treinosapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.matheus.treinosapp.R
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel
import com.matheus.treinosapp.presentation.common.MainAppBar
import com.matheus.treinosapp.presentation.home.components.Workouts
import com.matheus.treinosapp.presentation.home.components.WorkoutsCell


@Composable
fun HomeScreen(favoriteViewModel: FirestoreViewModel = hiltViewModel(),
               navController: NavController, isSystemInDarkTheme: Boolean,
               userData: UserData?,

               ) {

    Scaffold(
        topBar = {
            MainAppBar(
                icon1 = R.drawable.search,
                title = "Treinos",
                onLogoClick = {},
                onIconClick = {},
                isMainScreen = true
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {

            Workouts(userData = userData) {workouts ->
            if (workouts.isNotEmpty()) {
                WorkoutsCell(navController = navController,
                    workouts = workouts, "Treinos de outros usuários", false){}
                }
            }
        }
    }
}






