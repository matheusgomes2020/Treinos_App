package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.use_case.workouts.AddWorkout
import com.matheus.treinosapp.domain.use_case.workouts.DeleteWorkout
import com.matheus.treinosapp.domain.use_case.workouts.GetWorkouts

data class UseCases (
    val getWorkouts: GetWorkouts,
    val addWorkout: AddWorkout,
    val deleteWorkout: DeleteWorkout
)