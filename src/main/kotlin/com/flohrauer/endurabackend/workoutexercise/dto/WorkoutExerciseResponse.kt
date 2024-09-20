package com.flohrauer.endurabackend.workoutexercise.dto

import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import java.util.*

data class WorkoutExerciseResponse(
    val id: UUID,
    val exercise: ExerciseResponse
)
