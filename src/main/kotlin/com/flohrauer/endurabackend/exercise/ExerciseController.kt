package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.CreateExerciseRequest
import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.*

@Controller
@RequestMapping("/exercise")
class ExerciseController(private val exerciseService: ExerciseService) {

    @GetMapping
    fun getAll(
        @RequestParam(name = "name") nameFilter: String?,
        @RequestParam(name = "muscle_groups") muscleGroupsFilter: List<UUID>?
    ): ResponseEntity<List<ExerciseResponse>> {
        val exercises = exerciseService.getAll(nameFilter, muscleGroupsFilter)
        return ResponseEntity.ok(exercises)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<ExerciseResponse> {
        val exercise = exerciseService.getById(id)
        return ResponseEntity.ok(exercise)
    }

    @PostMapping
    fun create(@RequestBody createExerciseRequest: CreateExerciseRequest): ResponseEntity<ExerciseResponse> {
        val exercise = exerciseService.create(createExerciseRequest)

        return ResponseEntity
            .created(URI.create("/exercise/${exercise.id}"))
            .body(exercise)
    }
}
