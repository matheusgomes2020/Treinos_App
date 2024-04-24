package com.matheus.treinosapp.domain.use_case.workouts

import com.matheus.treinosapp.domain.repository.WorkoutsRepository

class GetWorkouts (
    private val repo: WorkoutsRepository
) {
    operator fun invoke() = repo.getWorkouts() }