package com.flohrauer.endurabackend.exercise.dto

data class CreateExerciseRequest(
    val name: String,
    val description: String?,
    val instructions: String?
)
