package com.flohrauer.endurabackend.workoutexercise

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkoutExerciseRepository: JpaRepository<WorkoutExercise, UUID> {
    fun findAllByWorkoutId(workoutId: UUID): List<WorkoutExercise>
}
