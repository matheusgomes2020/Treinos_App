package com.matheus.treinosapp.domain.use_case.exercises

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class GetExercises (
    private val repo: FirestoreRepository
) {
    operator fun invoke() = repo.getExercises() }