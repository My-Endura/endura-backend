package com.flohrauer.endurabackend.workout.dto

import java.util.*

data class CreateWorkoutRequest(
    val name: String,
    val workoutTemplateId: UUID?,
)
