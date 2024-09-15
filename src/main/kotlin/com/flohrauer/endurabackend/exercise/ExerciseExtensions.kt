package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse

fun Exercise.toResponse(): ExerciseResponse {
    return ExerciseResponse(id!!, name, description, instructions)
}
