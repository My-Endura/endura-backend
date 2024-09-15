package com.flohrauer.endurabackend.exercise.dto

import com.flohrauer.endurabackend.musclegroup.dto.MuscleGroupResponse
import java.util.*

data class ExerciseResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val instructions: String?,
    val muscleGroups: List<MuscleGroupResponse>
)
