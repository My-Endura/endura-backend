package com.flohrauer.endurabackend.workoutexercise

import com.flohrauer.endurabackend.exercise.ExerciseService
import com.flohrauer.endurabackend.workout.WorkoutService
import com.flohrauer.endurabackend.workoutexercise.dto.CreateWorkoutExerciseRequest
import com.flohrauer.endurabackend.workoutexercise.dto.WorkoutExerciseResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkoutExerciseService(
    private val workoutExerciseRepository: WorkoutExerciseRepository,
    private val exerciseService: ExerciseService,
    private val workoutService: WorkoutService,
) {

    fun getByWorkoutId(workoutId: UUID): List<WorkoutExerciseResponse> {
        val workoutExercises = workoutExerciseRepository.findAllByWorkoutId(workoutId)
        return workoutExercises.map { it.toResponse() }
    }

    fun create(workoutId: UUID, addWorkoutExerciseRequest: CreateWorkoutExerciseRequest): WorkoutExercise {
        val workout = workoutService.getEntityById(workoutId)
        val exercise = exerciseService.getEntityById(addWorkoutExerciseRequest.exerciseId)
        val entity = WorkoutExercise(workout, exercise)
        return workoutExerciseRepository.save(entity)
    }
}
