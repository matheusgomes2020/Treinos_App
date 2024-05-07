package com.matheus.treinosapp.presentation.exercise_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.treinosapp.R
import com.matheus.treinosapp.presentation.utils.AppBarWithBack
import com.matheus.treinosapp.presentation.utils.SubtitleHeader
import com.matheus.treinosapp.ui.DpDimensions

@Composable
fun ExerciseDetailScreen(
    navController: NavController,
    viewModel: ExerciseDetailViewModel = hiltViewModel()
    ) {

        val state = viewModel.state.value
    Log.d("XUXUA", "detail screen: ${state}")

        Scaffold(
            topBar = {
                AppBarWithBack(
                    title = state.exercise!!.name,
                    backIcon = Icons.AutoMirrored.Filled.ArrowBack,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        ) { it
            Column(
                modifier = Modifier.padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = R.drawable.weight
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = "poster image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                    )

                    SubtitleHeader(
                        title = "Observações",
                        modifier = Modifier.fillMaxWidth(),
                        isIconVisible = false,
                        isSystemInDarkTheme = true,
                        onClick = {

                        }
                    )
                    Text(text = state.exercise!!.observations)
                }
            }
        }
    }