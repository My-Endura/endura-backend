package com.flohrauer.endurabackend.exercise

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExerciseRepository: JpaRepository<Exercise, UUID>
