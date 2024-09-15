package com.flohrauer.endurabackend.workouttemplate

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkoutTemplateRepository: JpaRepository<WorkoutTemplate, UUID>
