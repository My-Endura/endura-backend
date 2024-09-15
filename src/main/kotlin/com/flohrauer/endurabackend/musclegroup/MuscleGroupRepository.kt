package com.flohrauer.endurabackend.musclegroup

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MuscleGroupRepository: JpaRepository<MuscleGroup, UUID>
