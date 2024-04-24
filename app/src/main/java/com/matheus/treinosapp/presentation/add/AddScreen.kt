package com.matheus.treinosapp.presentation.add

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.domain.repository.Workouts
import com.matheus.treinosapp.presentation.UserData
import com.matheus.treinosapp.presentation.favorites.FavoritesViewModel
import com.matheus.treinosapp.presentation.favorites.Workouts
import com.matheus.treinosapp.ui.DpDimensions
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
                "1234",
                "1",
                "exercício 1",
                timeStamp.toString(),
                userId = userData!!.userId
            )
        }) {
            Text(text = "Adicionar")
        }


        Workouts(userData = userData) {workouts ->
//            if (!workouts.isNullOrEmpty()) {
                WorkoutsCell(navController = navController,
                    workouts = workouts) {

              //  }
            }
        }

    }


}

@Composable
fun WorkoutsCell(
    navController: NavController,
    workouts: Workouts,
    deleteWorkout: (idFirebase: String) -> Unit
){

    Box(
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(workouts) { workout ->
                WorkoutListItemFirebase(
                    workout = workout,
                    onItemClick = {},
                    deleteMovie = {}
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WorkoutListItemFirebase(
    workout: Workout,
    deleteMovie: () -> Unit,
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
        WorkoutItem( name = workout.name, id = workout.id )
    }

}

@Composable
fun WorkoutItem(
    name: String,
    id: String
){
        Column(
            modifier = Modifier.padding(DpDimensions.Small)
        ) {
            Text(
                modifier = Modifier.width(110.dp),
                text = name,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                maxLines = 1
            )

            Text(
                modifier = Modifier.width(110.dp),
                text = id,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                maxLines = 1
            )

    }
}