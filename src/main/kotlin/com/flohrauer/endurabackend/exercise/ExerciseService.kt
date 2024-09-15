package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.CreateExerciseRequest
import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import com.flohrauer.endurabackend.exercise.exception.ExerciseAlreadyExistsException
import com.flohrauer.endurabackend.exercise.exception.ExerciseNotFoundException
import com.flohrauer.endurabackend.musclegroup.MuscleGroupService
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExerciseService(
    private val exerciseRepository: ExerciseRepository,
    private val muscleGroupService: MuscleGroupService
) {

    fun getAll(): List<ExerciseResponse> {
        val exercises = exerciseRepository.findAll()
        return exercises.map { it.toResponse() }
    }

    fun getById(id: UUID): ExerciseResponse {
        return getEntityById(id).toResponse()
    }

    fun create(createExerciseRequest: CreateExerciseRequest): ExerciseResponse {
        try {
            val muscleGroups = muscleGroupService
                .getAllEntitiesByIds(createExerciseRequest.muscleGroupsIds)
                .toSet()
            val exerciseEntity = createExerciseRequest.toEntity(muscleGroups)
            val databaseEntity = exerciseRepository.save(exerciseEntity)

            return databaseEntity.toResponse()
        } catch (e: DataIntegrityViolationException) {
            throw ExerciseAlreadyExistsException()
        }
    }

    private fun getEntityById(id: UUID): Exercise {
        return exerciseRepository.findById(id).orElseThrow {
            ExerciseNotFoundException()
        }
    }
}
