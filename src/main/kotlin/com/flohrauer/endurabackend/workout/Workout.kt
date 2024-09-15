package com.flohrauer.endurabackend.workout

import com.flohrauer.endurabackend.core.BaseEntity
import com.flohrauer.endurabackend.workouttemplate.WorkoutTemplate
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "workout")
class Workout(
    @Column(name = "name")
    val name: String,
    @Column(name = "started_at")
    val startedAt: LocalDateTime,
    @Column(name = "completed_at")
    var completedAt: LocalDateTime? = null,
    @Column(name = "duration")
    var duration: Long? = null,
    @Column(name = "total_weight")
    var totalWeight: Long? = null,
    @OneToOne
    @JoinColumn(name = "workout_template_id")
    val workoutTemplate: WorkoutTemplate? = null
): BaseEntity()
