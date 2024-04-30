package com.matheus.treinosapp.presentation.workout_detail

import com.matheus.treinosapp.domain.model.Workout

data class WorkoutDetailState (
    //val id: String = ""
    val isLoading: Boolean = false,
    val workout: Workout? = null,
    val error: String = ""
)