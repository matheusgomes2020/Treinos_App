package com.matheus.treinosapp.presentation.add_exercises

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.favorites.FavoritesViewModel
import com.matheus.treinosapp.ui.DpDimensions
import java.sql.Timestamp

@Composable
fun AddExercisesScreen(
    favoriteViewModel: FavoritesViewModel = hiltViewModel(),
    addExercisesViewModel: AddExercisesViewModel = hiltViewModel(),
              navController: NavController,
              isSystemInDarkTheme: Boolean,
              userData: UserData?
){

    val state = addExercisesViewModel.state.value

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    var nome by remember { mutableStateOf("") }
    var observacoes by remember { mutableStateOf("") }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else Color.DarkGray,
            darkIcons = useDarkIcons
        )
    }

    Column(
        modifier = Modifier
        //.background(Color.Green)
    ) {

        Text(text = "WORKOUT ID")
        Text(text = state.workoutId)

        Text(text = "USER NAME")
        Text(text = state.userName)


//        Text(text = "STATE")
//        Text(text = state.exercise!!.toString())
//
//        Text(text = "ID")
//        Text(text = state.exercise!!.idFirebase)
//
//        Text(text = "NAME")
//        Text(text = state.exercise!!.name)
//
//        Text(text = "IMAGE_URL")
//        Text(text = state.exercise!!.imageUrl)
//
//        Text(text = "OBSERVATIONS")
//        Text(text = state.exercise!!.observations)

        Column(
            modifier = Modifier
                .padding(DpDimensions.Normal)
        ) {
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
                        text = "Digite o nome do exercício",
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
                text = "Observações *",
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 20.sp
            )

            TextField(
                value = observacoes,
                onValueChange = {
                    observacoes = it
                },
                modifier = Modifier
                    .fillMaxWidth(),
//                        label = {Text(text = "Senha") },
                placeholder = {
                    Text(
                        text = "Observações do exercício",
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
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Adicionar imagem")
            }
            Spacer(modifier = Modifier.height(30.dp))
            Button(
                shape = RoundedCornerShape(DpDimensions.Normal),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    favoriteViewModel.addExercise(
                        "",
                        nome,
                        "https://conteudo.imguol.com.br/c/entretenimento/0e/2017/10/15/batata-crua-1508077604971_v2_450x450.jpg",
                        observacoes,
                        userId = userData!!.userId,
                        idWorkout = state.workoutId
                    ).let {it ->

                        navController.popBackStack()
                    }
                },
            ) {

                Text(text = "Salvar exercício", style = MaterialTheme.typography.titleMedium)
            }
        }

    }


}