package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.WorkoutsRepository
import java.sql.Timestamp

class AddWorkout(
    private val repo: WorkoutsRepository
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