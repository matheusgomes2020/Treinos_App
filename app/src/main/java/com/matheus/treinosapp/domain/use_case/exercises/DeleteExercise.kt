package com.matheus.treinosapp.domain.use_case.exercises

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class DeleteExercise(
    private val repo: FirestoreRepository
) {
    suspend operator fun invoke(idFirebase: String) = repo.deleteExercise( idFirebase )
}