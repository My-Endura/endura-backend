package com.flohrauer.endurabackend.workout.dto

import java.time.LocalDateTime
import java.util.*

data class WorkoutResponse(
    val id: UUID,
    val name: String,
    val startedAt: LocalDateTime,
    val completedAt: LocalDateTime? = null,
    val duration: Long? = null,
    val totalWeight: Long? = null
)
