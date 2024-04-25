package com.matheus.treinosapp.domain.model

import java.sql.Timestamp

data class Workout (
    val id: String,
    val name: String,
    val description: String,
    val timestamp: String,
    val userId: String,
    val username: String,
    var idFirebase: String
) {
    constructor() : this("", "", "", "", "", "", "")
}


