package com.matheus.treinosapp.domain.model

import java.sql.Timestamp

data class Workout (
    var id: String,
    var name: String,
    var description: String,
    var timestamp: String,
    var userId: String,
    var username: String,
    var idFirebase: String
) {
    constructor() : this("", "", "", "", "", "", "")
}


