package com.flohrauer.endurabackend.musclegroup

import com.flohrauer.endurabackend.musclegroup.dto.MuscleGroupResponse
import com.flohrauer.endurabackend.musclegroup.exception.MuscleGroupNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class MuscleGroupService(private val muscleGroupRepository: MuscleGroupRepository) {

    fun getAll(): List<MuscleGroupResponse> {
        val muscleGroups = muscleGroupRepository.findAll()
        return muscleGroups.map { it.toResponse() }
    }

    fun getById(id: UUID): MuscleGroupResponse {
        return getEntityById(id).toResponse()
    }

    fun getAllEntitiesByIds(ids: List<UUID>): List<MuscleGroup> {
        return muscleGroupRepository.findAllById(ids)
    }

    private fun getEntityById(id: UUID): MuscleGroup {
        return muscleGroupRepository.findById(id).orElseThrow {
            MuscleGroupNotFoundException()
        }
    }
}
