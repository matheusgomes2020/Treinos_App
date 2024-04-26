package com.matheus.treinosapp.domain.use_case.exercises

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class AddExercise(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(

        id: String,
        name: String,
        imageUrl: String,
        observations: String,
        userId: String,
        idWorkout: String,
    ) = repo.addExercise(id, name, imageUrl, observations, userId, idWorkout)
}