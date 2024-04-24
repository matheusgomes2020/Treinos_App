package com.matheus.treinosapp.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.favorites.FavoritesViewModel
import java.sql.Timestamp

@Composable
fun AddScreen(favoriteViewModel: FavoritesViewModel = hiltViewModel(),
              navController: NavController, isSystemInDarkTheme: Boolean,
              userData: UserData?,){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else Color.DarkGray,
            darkIcons = useDarkIcons
        )
    }

    Column {
        Text(text = "Add Screen")
        Button(onClick = {
            val currentTimeMillis = System.currentTimeMillis()
            val timeStamp = Timestamp(currentTimeMillis)
            favoriteViewModel.addWorkout(
                1234,
                1,
                "exercício 1",
                timeStamp,
                userId = userData!!.userId
            )
        }) {
            Text(text = "Adicionar")
        }
    }


}