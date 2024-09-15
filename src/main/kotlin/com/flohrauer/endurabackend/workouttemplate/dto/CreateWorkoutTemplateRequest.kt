package com.flohrauer.endurabackend.workouttemplate.dto

import java.util.*

data class CreateWorkoutTemplateRequest(
    val name: String,
    val description: String?,
    val exercisesIds: List<UUID>,
)
