package com.matheus.treinosapp.domain.model

import java.sql.Timestamp

data class Exercise (
    var id: String,
    var name: String,
    var imageUrl: String,
    var observations: String,
    var userId: String,
    var idFirebase: String,
    var idWorkout: String
) {
    constructor() : this("", "", "", "", "", "", "")
}