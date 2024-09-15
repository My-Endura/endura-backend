package com.flohrauer.endurabackend.musclegroup

import com.flohrauer.endurabackend.core.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "muscle_group")
class MuscleGroup(
    @Column(name = "name")
    val name: String
): BaseEntity()
