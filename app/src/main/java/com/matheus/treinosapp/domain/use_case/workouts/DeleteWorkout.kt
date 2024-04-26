package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class DeleteWorkout(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(idFirebase: String) = repo.deleteWorkout( idFirebase )
}