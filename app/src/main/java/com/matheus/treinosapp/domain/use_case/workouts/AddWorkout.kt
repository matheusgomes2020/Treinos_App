package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class AddWorkout(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(

        id: String,
        name: String,
        description: String,
        timestamp: String,
        userId: String,
        userName: String,
    ) = repo.addWorkout(id, name, description, timestamp, userId, userName)
}