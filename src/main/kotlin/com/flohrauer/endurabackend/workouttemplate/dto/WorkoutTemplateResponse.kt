package com.flohrauer.endurabackend.workouttemplate.dto

import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import java.time.LocalDateTime
import java.util.*

data class WorkoutTemplateResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val lastCompleted: LocalDateTime?,
    val exercises: List<ExerciseResponse>
)
