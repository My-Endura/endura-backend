package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.core.BaseEntity
import com.flohrauer.endurabackend.exercise.Exercise
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "workout_template")
class WorkoutTemplate(
    @Column(name = "name")
    var name: String,
    @Column(name = "description", nullable = true)
    var description: String?,
    @Column(name = "last_completed", nullable = true)
    var lastCompleted: LocalDateTime? = null,
    @OneToMany
    @JoinTable(
        name = "workout_template_exercise",
        joinColumns = [JoinColumn(name = "workout_template_id")],
        inverseJoinColumns = [JoinColumn(name = "exercise_id")]
    )
    val exercises: MutableSet<Exercise> = mutableSetOf()
): BaseEntity()
