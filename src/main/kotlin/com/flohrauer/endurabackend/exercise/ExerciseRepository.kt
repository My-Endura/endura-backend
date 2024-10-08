package com.flohrauer.endurabackend.exercise

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.*

interface ExerciseRepository: JpaRepository<Exercise, UUID>, JpaSpecificationExecutor<Exercise>
