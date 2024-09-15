package com.flohrauer.endurabackend.workout

import com.flohrauer.endurabackend.workout.dto.CreateWorkoutRequest
import com.flohrauer.endurabackend.workout.dto.WorkoutResponse
import com.flohrauer.endurabackend.workouttemplate.WorkoutTemplate
import java.time.LocalDateTime

fun Workout.toResponse(): WorkoutResponse {
    return WorkoutResponse(
        id = id!!,
        name = name,
        startedAt = startedAt,
        completedAt = completedAt,
        duration = duration,
        totalWeight = totalWeight
    )
}

fun CreateWorkoutRequest.toEntity(workoutTemplate: WorkoutTemplate?): Workout {
    return Workout(
        name = name,
        startedAt = LocalDateTime.now(),
        workoutTemplate = workoutTemplate
    )
}

fun Workout.isCompleted(): Boolean {
    return completedAt != null
}
