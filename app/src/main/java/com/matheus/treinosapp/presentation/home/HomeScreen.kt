package com.matheus.treinosapp.presentation.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.matheus.treinosapp.R
import com.matheus.treinosapp.presentation.MainAppBar
import com.matheus.treinosapp.presentation.signIn.AuthViewModel


@Composable
fun HomeScreen(navController: NavController = rememberNavController(),

) {
    //val systemUiController = rememberSystemUiController()




    Scaffold(
        topBar = {
            MainAppBar(
                icon1 = R.drawable.search,
                title = "Treinos",
                imageUrl = "",
                onLogoClick = {},
                onSearchClick = {
                    //navController.navigate( AppGraph.search_movies.SEARCH_MOVIES + "/${" "}" )
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()

        ) {
            Text(text = "Olá")
        }
    }
}