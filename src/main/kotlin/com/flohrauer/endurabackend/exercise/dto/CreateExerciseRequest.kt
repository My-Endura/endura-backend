package com.flohrauer.endurabackend.exercise.dto

import java.util.*

data class CreateExerciseRequest(
    val name: String,
    val description: String?,
    val instructions: String?,
    val muscleGroupsIds: List<UUID>
)
