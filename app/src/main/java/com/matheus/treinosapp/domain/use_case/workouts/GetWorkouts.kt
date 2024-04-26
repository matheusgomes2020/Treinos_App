package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.FirestoreRepository

class GetWorkouts (
    private val repo: FirestoreRepository
) {
    operator fun invoke() = repo.getWorkouts() }