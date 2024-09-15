package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.exercise.Exercise
import com.flohrauer.endurabackend.exercise.toResponse
import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.WorkoutTemplateResponse

fun WorkoutTemplate.toResponse(): WorkoutTemplateResponse {
    return WorkoutTemplateResponse(
        id = id!!,
        name = name,
        description = description,
        lastCompleted = lastCompleted,
        exercises = exercises
            .map { it.toResponse() }
            .sortedBy { it.name }
    )
}

fun CreateWorkoutTemplateRequest.toEntity(exercises: Set<Exercise>): WorkoutTemplate {
    return WorkoutTemplate(
        name = name,
        description = description,
        exercises = exercises
    )
}
