package com.matheus.treinosapp.presentation.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.treinosapp.R
import com.matheus.treinosapp.presentation.signIn.components.UserData
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel
import com.matheus.treinosapp.presentation.common.MainAppBar
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.OrangeApp
import java.sql.Timestamp

@Composable
fun AddScreen(favoriteViewModel: FirestoreViewModel = hiltViewModel(),
              navController: NavController,
              isSystemInDarkTheme: Boolean,
              userData: UserData?
){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    var nome by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }

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
                icon1 = R.drawable.search,
                title = "Adicionar treino",
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
            Column(
                modifier = Modifier
                    .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)) {
                Text(
                    text = "Nome *",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 20.sp
                )

                TextField(
                    value = nome,
                    onValueChange = {
                        nome = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
//                        label = {Text(text = "Senha") },
                    placeholder = {
                        Text(
                            text = "Digite o nome do treino",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Magenta,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.Red,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),                keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    )
                )

                Text(
                    text = "Descrição *",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 20.sp
                )

                TextField(
                    value = descricao,
                    onValueChange = {
                        descricao = it
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
//                        label = {Text(text = "Senha") },
                    placeholder = {
                        Text(
                            text = "Digite o descrição do treino",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Magenta,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray,
                        disabledIndicatorColor = Color.Red,
                        cursorColor = MaterialTheme.colorScheme.onPrimary
                    ),                keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        //contentColor = Color.Green,
                        containerColor = OrangeApp
                    ),
                    shape = RoundedCornerShape(DpDimensions.Normal),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        val currentTimeMillis = System.currentTimeMillis()
                        val timeStamp = Timestamp(currentTimeMillis)

                        favoriteViewModel.addWorkout(
                            "",
                            nome,
                            descricao,
                            timeStamp.toString(),
                            userId = userData!!.userId,
                            userName = userData.username!!
                        ).let {it ->

                            navController.popBackStack()
                        }
                    },
                ) {

                    Text(text = "Salvar", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}


