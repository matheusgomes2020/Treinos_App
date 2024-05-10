package com.matheus.treinosapp.presentation.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.matheus.treinosapp.domain.model.Workout
import com.matheus.treinosapp.ui.DpDimensions
import com.matheus.treinosapp.ui.theme.Orange
import com.matheus.treinosapp.ui.theme.OrangeApp

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