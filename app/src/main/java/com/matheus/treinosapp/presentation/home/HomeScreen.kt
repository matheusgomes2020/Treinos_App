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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.matheus.treinosapp.R
import com.matheus.treinosapp.domain.model.Exercise
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.Exercises
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.navigation.AppGraph
import com.matheus.treinosapp.presentation.MainAppBar
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.favorites.Exercises
import com.matheus.treinosapp.presentation.favorites.FavoritesViewModel
import com.matheus.treinosapp.presentation.favorites.Workouts
import com.matheus.treinosapp.presentation.signIn.AuthViewModel
import com.matheus.treinosapp.ui.DpDimensions
import java.sql.Timestamp


@Composable
fun HomeScreen(favoriteViewModel: FavoritesViewModel = hiltViewModel(),
               navController: NavController, isSystemInDarkTheme: Boolean,
               userData: UserData?,

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
            Button(onClick = {
                val currentTimeMillis = System.currentTimeMillis()
                val timeStamp = Timestamp(currentTimeMillis)
                favoriteViewModel.addWorkout(
                    "43434234",
                    "3",
                    "Treino de costas",
                    timeStamp.toString(),
                    userId = userData!!.userId,
                    userName = userData.username!!
                )
            }) {
                Text(text = "Adicionar")
            }

            Button(onClick = {
                favoriteViewModel.addExercise(
                    id = "0012345678910",
                    name = "7",
                    imageUrl = "",
                    observations = "7 repetições de 15",
                    userId = userData!!.userId,
                    idWorkout = "para depois"

                )
            }) {
                Text(text = "Adicionar exercício")
            }


            Workouts(userData = userData) {workouts ->
//            if (!workouts.isNullOrEmpty()) {
                WorkoutsCell(navController = navController,
                    workouts = workouts) {

                    //  }
                }
            }

            Exercises(userData = userData) {exercises ->
//            if (!workouts.isNullOrEmpty()) {
                ExercisesCell(navController = navController,
                    exercises = exercises) {

                    //  }
                }
            }
        }
    }
}


@Composable
fun TextsFields(){

}


@Composable
fun WorkoutsCell(
    navController: NavController,
    workouts: Workouts,
    deleteWorkout: (idFirebase: String) -> Unit
){

    Column(
    ) {
        Text(text = "Treinos")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(workouts) { workout ->
                WorkoutListItemFirebase(
                    workout = workout,
                    onItemClick = {
                        val routes = AppGraph.workouts_details.DETAILS + "/${workout.idFirebase}/${workout.name}/${workout.description}/${workout.timestamp}/${workout.username}"
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
    ) {
        Text(text = "Exercícios")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(exercises) { exercise ->
                ExerciseListItemFirebase(
                    exercise = exercise,
                    onItemClick = {
                                  navController.navigate(AppGraph.workouts_details.DETAILS)
                    },
                    deleteExercise = {}
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
fun ExerciseListItemFirebase(
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
        Column(
            modifier = Modifier.padding(DpDimensions.Small)
        ) {
            Text(
                modifier = Modifier.width(110.dp),
                text = exercise.name,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                maxLines = 1
            )

            Text(
                modifier = Modifier.width(110.dp),
                text = exercise.observations,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                //maxLines = 1
            )
            Text(
                modifier = Modifier.width(110.dp),
                text = exercise.idWorkout,
                style = MaterialTheme.typography.titleSmall,
                //color = Color.White,
                maxLines = 1
            )

        }
    }

}

@Composable
fun WorkoutItem(
    name: String,
    description: String,
    id: String,
    user: String
){
    Column(
        modifier = Modifier.padding(DpDimensions.Small)
    ) {
        Text(
            modifier = Modifier.width(110.dp),
            text = name,
            style = MaterialTheme.typography.titleSmall,
            //color = Color.White,
            maxLines = 1
        )

        Text(
            modifier = Modifier.width(110.dp),
            text = description,
            style = MaterialTheme.typography.titleSmall,
            //color = Color.White,
            //maxLines = 1
        )
        Text(
            modifier = Modifier.width(110.dp),
            text = user,
            style = MaterialTheme.typography.titleSmall,
            //color = Color.White,
            maxLines = 1
        )

    }
}