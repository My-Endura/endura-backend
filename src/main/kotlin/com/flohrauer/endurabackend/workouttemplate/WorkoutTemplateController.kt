package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
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
}
