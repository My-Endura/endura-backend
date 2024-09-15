package com.flohrauer.endurabackend.musclegroup

import com.flohrauer.endurabackend.musclegroup.dto.MuscleGroupResponse

fun MuscleGroup.toResponse(): MuscleGroupResponse {
    return MuscleGroupResponse(id!!, name)
}
