package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.core.BaseEntity
import com.flohrauer.endurabackend.musclegroup.MuscleGroup
import jakarta.persistence.*

@Entity
@Table(name = "exercise")
class Exercise(
    @Column(name = "name")
    val name: String,
    @Column(name = "description", nullable = true)
    val description: String?,
    @Column(name = "instructions", nullable = true)
    val instructions: String?,
    @OneToMany
    @JoinTable(
        name = "exercise_muscle_group",
        joinColumns = [JoinColumn(name = "exercise_id")],
        inverseJoinColumns = [JoinColumn(name = "muscle_group_id")]
    )
    val muscleGroups: Set<MuscleGroup> = emptySet()
): BaseEntity()
