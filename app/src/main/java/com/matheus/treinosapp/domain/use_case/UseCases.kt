package com.matheus.treinosapp.domain.use_case

import com.matheus.treinosapp.domain.use_case.exercises.AddExercise
import com.matheus.treinosapp.domain.use_case.exercises.DeleteExercise
import com.matheus.treinosapp.domain.use_case.exercises.GetExercises
import com.matheus.treinosapp.domain.use_case.workouts.AddWorkout
import com.matheus.treinosapp.domain.use_case.workouts.DeleteWorkout
import com.matheus.treinosapp.domain.use_case.workouts.GetWorkouts

data class UseCases (
    val getWorkouts: GetWorkouts,
    val addWorkout: AddWorkout,
    val deleteWorkout: DeleteWorkout,

    val getExercises: GetExercises,
    val addExercise: AddExercise,
    val deleteExercise: DeleteExercise
)