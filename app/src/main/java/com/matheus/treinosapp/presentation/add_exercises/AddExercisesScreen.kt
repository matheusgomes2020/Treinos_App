package com.matheus.treinosapp.presentation.add_exercises

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.treinosapp.R
import com.matheus.treinosapp.common.Constants.ALL_IMAGES
import com.matheus.treinosapp.domain.model.Response
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.image.AbrirGaleria
import com.matheus.treinosapp.presentation.image.AddImageToStorage
import com.matheus.treinosapp.presentation.image.ImageViewModel
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel
import com.matheus.treinosapp.presentation.utils.AppBarWithBack
import com.matheus.treinosapp.presentation.utils.ProgressBar
import com.matheus.treinosapp.ui.DpDimensions

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddExercisesScreen(
    favoriteViewModel: FirestoreViewModel = hiltViewModel(),
    imageViewModel: ImageViewModel = hiltViewModel(),
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
    var imageUrlP by remember { mutableStateOf("") }

    val imageUri2 = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri2.value.ifEmpty { R.drawable.weight }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { imageUri ->
        imageUri?.let {
            imageViewModel.addImageToStorage(imageUri, state.workoutId)
            imageUri2.value = it.toString()
        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else Color.DarkGray,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Adicionar exercício",
                backIcon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { it ->
        Column(
            modifier = Modifier.padding(it)
        ) {
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
                Button(onClick = {
                    launcher.launch(
                        ALL_IMAGES
                    )
                }) {
                    Text(text = "Adicionar imagem")
                }
                Image(painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(250.dp),
                )
                Spacer(modifier = Modifier.height(30.dp))
                AbrirGaleria(
                    openGallery = {
                        launcher.launch(ALL_IMAGES)
                    }
                )
                when (val addImageToStorageResponse = imageViewModel.addImageUrlToStorageResponse) {
                    is Response.Loading -> ProgressBar()
                    is Response.Success -> addImageToStorageResponse.data?.let { downloadUrl ->
                        LaunchedEffect(downloadUrl) {
                            imageUrlP = downloadUrl.toString()
                        }
                    }
                    is Response.Failure -> print(addImageToStorageResponse.e)
                }
//                AddImageToStorage(
//                    addImageToDatabase =  { downloadUrl ->
//                        imageViewModel.addImageToDatabase(downloadUrl)
//                    }
//                ).let { it ->
//                    Log.d("COMPLETOU?", "AddExercisesScreen:   | $it ")
//                }
//                when (val getFromDatabaseResponse = imageViewModel.getImageFromDatabaseResponse) {
//                    is Response.Loading -> ProgressBar()
//                    is Response.Success -> getFromDatabaseResponse.data?.let { imageUrl ->
//                        imageUrlP = imageUrl
//                        Log.d("COMPLETOU?", "AddExercisesScreen: $imageUrl   | $imageUrlP ")
//                    }
//                    is Response.Failure -> print(getFromDatabaseResponse.e)
//                }

                Log.d("COMPLETOU?", "DEpois do método:   | $imageUrlP ")

                Button(
                    shape = RoundedCornerShape(DpDimensions.Normal),
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        favoriteViewModel.addExercise(
                            "",
                            nome,
                            imageUrlP,
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




}