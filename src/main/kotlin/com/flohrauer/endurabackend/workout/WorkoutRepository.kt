package com.flohrauer.endurabackend.workout

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkoutRepository: JpaRepository<Workout, UUID>
