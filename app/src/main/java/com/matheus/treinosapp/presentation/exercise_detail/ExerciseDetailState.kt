package com.matheus.treinosapp.presentation.exercise_detail

import com.matheus.treinosapp.domain.model.Exercise

data class ExerciseDetailState (
    //val id: String = ""
    val isLoading: Boolean = false,
    val exercise: Exercise? = null,
    val error: String = ""
)