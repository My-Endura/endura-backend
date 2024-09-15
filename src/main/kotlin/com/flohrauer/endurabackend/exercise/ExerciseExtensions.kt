package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.CreateExerciseRequest
import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import com.flohrauer.endurabackend.musclegroup.MuscleGroup
import com.flohrauer.endurabackend.musclegroup.toResponse

fun Exercise.toResponse(): ExerciseResponse {
    return ExerciseResponse(
        id = id!!,
        name = name,
        description = description,
        instructions = instructions,
        muscleGroups = muscleGroups
            .map { it.toResponse() }
            .sortedBy { it.name }
    )
}

fun CreateExerciseRequest.toEntity(muscleGroups: Set<MuscleGroup>): Exercise {
    return Exercise(name, description, instructions, muscleGroups)
}
