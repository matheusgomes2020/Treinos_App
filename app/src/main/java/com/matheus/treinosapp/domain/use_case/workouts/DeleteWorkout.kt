package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.WorkoutsRepository

class DeleteWorkout(
    private val repo: WorkoutsRepository
) {
    suspend operator fun invoke(idFirebase: String) = repo.deleteWorkout( idFirebase )
}