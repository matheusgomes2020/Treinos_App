package com.matheus.treinosapp.domain.model

import java.sql.Timestamp

data class Exercise (
    val id: String,
    val name: Int,
    val imageUrl: String,
    val observations: String,
    val userId: String,
    var idFirebase: String
)