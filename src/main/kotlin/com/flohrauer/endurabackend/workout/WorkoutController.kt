package com.flohrauer.endurabackend.workout

import com.flohrauer.endurabackend.workout.dto.CreateWorkoutRequest
import com.flohrauer.endurabackend.workout.dto.WorkoutResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/workout")
class WorkoutController(private val workoutService: WorkoutService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<WorkoutResponse>> {
        val workouts = workoutService.getAll()
        return ResponseEntity.ok(workouts)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<WorkoutResponse> {
        val workout = workoutService.getById(id)
        return ResponseEntity.ok(workout)
    }

    @PostMapping
    fun create(
        @RequestBody createWorkoutRequest: CreateWorkoutRequest
    ): ResponseEntity<WorkoutResponse> {
        val workout = workoutService.create(createWorkoutRequest)

        return ResponseEntity
            .created(URI.create("/workout/${workout.id}"))
            .body(workout)
    }

    @PutMapping("/{id}/complete")
    fun complete(@PathVariable id: UUID): ResponseEntity<WorkoutResponse> {
        val workout = workoutService.complete(id)
        return ResponseEntity.ok(workout)
    }
}
