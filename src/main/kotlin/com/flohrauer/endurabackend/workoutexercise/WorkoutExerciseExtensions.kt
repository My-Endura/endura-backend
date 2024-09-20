package com.flohrauer.endurabackend.workoutexercise

import com.flohrauer.endurabackend.exercise.toResponse
import com.flohrauer.endurabackend.workoutexercise.dto.WorkoutExerciseResponse

fun WorkoutExercise.toResponse(): WorkoutExerciseResponse {
    return WorkoutExerciseResponse(
        id = id!!,
        exercise = exercise.toResponse()
    )
}
