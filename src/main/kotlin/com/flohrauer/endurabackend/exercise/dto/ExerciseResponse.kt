package com.flohrauer.endurabackend.exercise.dto

import java.util.*

data class ExerciseResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val instructions: String?
)
