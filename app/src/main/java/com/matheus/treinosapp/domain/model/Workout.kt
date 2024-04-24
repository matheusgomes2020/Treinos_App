package com.matheus.treinosapp.domain.model

import java.sql.Timestamp

data class Workout (
    val id: String,
    val name: Int,
    val description: String,
    val timestamp: Timestamp,
    val userId: String,
    var idFirebase: String
)
