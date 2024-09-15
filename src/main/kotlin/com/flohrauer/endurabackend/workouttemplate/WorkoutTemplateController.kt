package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.workouttemplate.dto.AddExerciseRequest
import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.UpdateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.WorkoutTemplateResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@RestController
@RequestMapping("/workout-template")
class WorkoutTemplateController(private val workoutTemplateService: WorkoutTemplateService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<WorkoutTemplateResponse>> {
        val workoutTemplates = workoutTemplateService.getAll()
        return ResponseEntity.ok(workoutTemplates)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<WorkoutTemplateResponse> {
        val workoutTemplate = workoutTemplateService.getById(id)
        return ResponseEntity.ok(workoutTemplate)
    }

    @PostMapping
    fun create(
        @RequestBody createWorkoutTemplateRequest: CreateWorkoutTemplateRequest
    ): ResponseEntity<WorkoutTemplateResponse> {
        val workoutTemplate = workoutTemplateService.create(createWorkoutTemplateRequest)

        return ResponseEntity
            .created(URI.create("/workout-template/${workoutTemplate.id}"))
            .body(workoutTemplate)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: UUID,
        @RequestBody updateWorkoutTemplateRequest: UpdateWorkoutTemplateRequest
    ): ResponseEntity<WorkoutTemplateResponse> {
        val workoutTemplate = workoutTemplateService.update(id, updateWorkoutTemplateRequest)
        return ResponseEntity.ok(workoutTemplate)
    }

    @PostMapping("/{id}/exercise")
    fun addExercise(
        @PathVariable id: UUID,
        @RequestBody addExerciseRequest: AddExerciseRequest
    ): ResponseEntity<WorkoutTemplateResponse> {
        val exercise = workoutTemplateService.addExercise(id, addExerciseRequest)

        return ResponseEntity
            .created(URI.create("/workout-template/$id"))
            .body(exercise)
    }

    @DeleteMapping("/{id}/exercise/{exerciseId}")
    fun removeExercise(
        @PathVariable id: UUID,
        @PathVariable exerciseId: UUID
    ): ResponseEntity<Unit> {
        workoutTemplateService.removeExercise(id, exerciseId)
        return ResponseEntity.noContent().build()
    }
}
