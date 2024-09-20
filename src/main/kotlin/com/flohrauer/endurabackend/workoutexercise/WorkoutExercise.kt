package com.flohrauer.endurabackend.workoutexercise

import com.flohrauer.endurabackend.core.BaseEntity
import com.flohrauer.endurabackend.exercise.Exercise
import com.flohrauer.endurabackend.workout.Workout
import jakarta.persistence.*

@Entity
@Table(name = "workout_exercise")
class WorkoutExercise(
    @ManyToOne
    @JoinColumn(name = "workout_id")
    val workout: Workout,
    @OneToOne
    @JoinColumn(name = "exercise_id")
    val exercise: Exercise
): BaseEntity()
