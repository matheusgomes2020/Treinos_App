package com.matheus.treinosapp.presentation.add_exercises

import com.matheus.treinosapp.domain.model.Exercise

data class ExerciseDetailState (
    val isLoading: Boolean = false,
    val exercise: Exercise? = null,
    val error: String = ""
)