package com.flohrauer.endurabackend.musclegroup

import com.flohrauer.endurabackend.musclegroup.dto.MuscleGroupResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.UUID

@Controller
@RequestMapping("/muscle-group")
class MuscleGroupController(private val muscleGroupService: MuscleGroupService) {

    @GetMapping
    fun getAll(): ResponseEntity<List<MuscleGroupResponse>> {
        val muscleGroups = muscleGroupService.getAll()
        return ResponseEntity.ok(muscleGroups)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<MuscleGroupResponse> {
        val muscleGroup = muscleGroupService.getById(id)
        return ResponseEntity.ok(muscleGroup)
    }
}
