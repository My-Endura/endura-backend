package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.exercise.ExerciseService
import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.WorkoutTemplateResponse
import com.flohrauer.endurabackend.workouttemplate.exception.WorkoutTemplateAlreadyExistsException
import com.flohrauer.endurabackend.workouttemplate.exception.WorkoutTemplateNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.*

@Service
class WorkoutTemplateService(
    private val workoutTemplateRepository: WorkoutTemplateRepository,
    private val exerciseService: ExerciseService
) {

    fun getAll(): List<WorkoutTemplateResponse> {
        val workoutTemplateEntities = workoutTemplateRepository.findAll()
        return workoutTemplateEntities.map { it.toResponse() }
    }

    fun getById(id: UUID): WorkoutTemplateResponse {
        return getEntityById(id).toResponse()
    }

    private fun getEntityById(id: UUID): WorkoutTemplate {
        return workoutTemplateRepository.findById(id).orElseThrow {
            WorkoutTemplateNotFoundException()
        }
    }

    fun create(createWorkoutTemplateRequest: CreateWorkoutTemplateRequest): WorkoutTemplateResponse {
        try {
            val exercises = exerciseService.getAllEntitiesByIds(createWorkoutTemplateRequest.exercisesIds)
            val workoutTemplateEntity = createWorkoutTemplateRequest.toEntity(exercises.toSet())
            val databaseTemplateEntity = workoutTemplateRepository.save(workoutTemplateEntity)

            return databaseTemplateEntity.toResponse()
        } catch (e: DataIntegrityViolationException) {
            throw WorkoutTemplateAlreadyExistsException()
        }
    }
}
