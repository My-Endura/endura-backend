package com.flohrauer.endurabackend.workoutexercise

import com.flohrauer.endurabackend.workoutexercise.dto.CreateWorkoutExerciseRequest
import com.flohrauer.endurabackend.workoutexercise.dto.WorkoutExerciseResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/workout/{workoutId}/exercise")
class WorkoutExerciseController (private val workoutExerciseService: WorkoutExerciseService) {

    @GetMapping
    fun getByWorkoutId(@PathVariable workoutId: UUID): ResponseEntity<List<WorkoutExerciseResponse>> {
        val workoutExercises = workoutExerciseService.getByWorkoutId(workoutId)
        return ResponseEntity.ok(workoutExercises)
    }

    @PostMapping
    fun create(
        @PathVariable workoutId: UUID,
        @RequestBody createWorkoutExerciseRequest: CreateWorkoutExerciseRequest
    ): ResponseEntity<Unit> {
        workoutExerciseService.create(workoutId, createWorkoutExerciseRequest)

        return ResponseEntity
            .created(URI.create("/workout/$workoutId"))
            .build()
    }
}
