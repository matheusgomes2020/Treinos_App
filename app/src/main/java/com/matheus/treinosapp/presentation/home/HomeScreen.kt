package com.matheus.treinosapp.presentation.home


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.matheus.treinosapp.R
import com.matheus.treinosapp.domain.model.Exercise
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.Exercises
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.profile.FirestoreViewModel
import com.matheus.treinosapp.presentation.profile.Workouts
import com.matheus.treinosapp.presentation.utils.MainAppBar
import com.matheus.treinosapp.presentation.utils.SubtitleHeader
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.Orange
import com.matheus.treinosapp.ui.theme.OrangeApp


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


@Composable
fun WorkoutsCell(
    navController: NavController,
    workouts: Workouts,
    text: String,
    isProfileScreen: Boolean,
    deleteWorkout: (idFirebase: String) -> Unit,
){

    var isProfile = if (isProfileScreen) "true" else "false"

    Column(
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)
    ) {
        SubtitleHeader(
            title = text,
            modifier = Modifier.fillMaxWidth(),
            isIconVisible = true,
            isSystemInDarkTheme = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(workouts) { workout ->
                WorkoutListItem(
                    workout = workout,
                    onItemClick = {
                        val routes = AppGraph.workouts_details.DETAILS + "/${workout.idFirebase}/${workout.name}/${workout.description}/${workout.timestamp}/${workout.username}/${isProfile}"
                        Log.d("FORA", "WorkoutsCell: $routes")
                        navController.navigate(routes)
                    },
                    deleteWorkout = {}
                )
            }
        }
    }
}

@Composable
fun ExercisesCell(
    navController: NavController,
    exercises: Exercises,
    deleteExercise: (idFirebase: String) -> Unit
){

    Column(
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = DpDimensions.Normal)
    ) {
        SubtitleHeader(
            title = "Exercícios",
            modifier = Modifier.fillMaxWidth(),
            isIconVisible = true,
            isSystemInDarkTheme = true,
            onClick = {

            }
        )
        Spacer(modifier = Modifier.height(DpDimensions.Small))
        if (!exercises.isNullOrEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(exercises) { exercise ->

                    ExerciseListItemFirebase2(
                        exercise = exercise,
                        onItemClick = {
                            navController.navigate(AppGraph.exercises_details.DETAILS+ "/${exercise.idFirebase}/${exercise.name}/${exercise.observations}" )
                                   // "/${exercise.name}/${exercise.userId}/${exercise.observations}/${exercise.imageUrl}")
                        },
                        deleteExercise = {}
                    )
                }

            }
        } else {
            Text(text = "Este treino não tem exercícios!!!")
        }

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListItem(
    workout: Workout,
    deleteWorkout: () -> Unit,
    onItemClick: (Workout) -> Unit,
    height: Dp = 250.dp

) {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState()
    var isLogoutSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(200.dp)
            .height(height)
            .combinedClickable(
                onClick = { onItemClick(workout) },
                onLongClick = {
                    isLogoutSheetOpen = true
                }
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .paint(
//                    painter = rememberAsyncImagePainter(model = R.drawable.weight),
//                    contentScale = ContentScale.Crop
//                )
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Orange,
                            Orange,
                            OrangeApp,
                        )
                    ),
                )
                .clip(RoundedCornerShape(DpDimensions.Small)),
            //contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(DpDimensions.Small)
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = workout.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(DpDimensions.Normal))

                Text(
                    modifier = Modifier.height(160.dp),
                    text = workout.description,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    maxLines = 3
                )
                //Spacer(modifier = Modifier.height(DpDimensions.Dp100))

                    Text(
                        modifier = Modifier.width(110.dp),
                        text = "Por: ${workout.username}",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.White,
                        maxLines = 1
                    )
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListItemFirebase(
    workout: Workout,
    deleteWorkout: () -> Unit,
    onItemClick: (Workout) -> Unit,
    height: Dp = 170.dp

) {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState()
    var isLogoutSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height)
            .combinedClickable(
                onClick = { onItemClick(workout) },
                onLongClick = {
                    isLogoutSheetOpen = true
                }
            ),
    ) {
        Column(
            modifier = Modifier.padding(DpDimensions.Small)
        ) {
            Text(
                modifier = Modifier.width(110.dp),
                text = workout.name,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                maxLines = 1
            )

            Text(
                modifier = Modifier.width(110.dp),
                text = workout.description,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                //maxLines = 1
            )
            Text(
                modifier = Modifier.width(110.dp),
                text = workout.username,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                maxLines = 1
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ExerciseListItemFirebase2(
    exercise: Exercise,
    deleteExercise: () -> Unit,
    onItemClick: (Exercise) -> Unit,
    height: Dp = 170.dp

) {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState()
    var isLogoutSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height)
            .combinedClickable(
                onClick = { onItemClick(exercise) },
                onLongClick = {
                    isLogoutSheetOpen = true
                }
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(
                        model = if (exercise.imageUrl.isNullOrEmpty())
                            R.drawable.weight else exercise.imageUrl) ,
                    contentScale = ContentScale.Crop
                )
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    ),
                )
                .clip(RoundedCornerShape(DpDimensions.Small)),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier.padding(DpDimensions.Small)
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = exercise.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }

    }

}
