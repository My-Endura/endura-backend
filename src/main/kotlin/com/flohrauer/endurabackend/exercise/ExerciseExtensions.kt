package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.CreateExerciseRequest
import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse

fun Exercise.toResponse(): ExerciseResponse {
    return ExerciseResponse(id!!, name, description, instructions)
}

fun CreateExerciseRequest.toEntity(): Exercise {
    return Exercise(name, description, instructions)
}
