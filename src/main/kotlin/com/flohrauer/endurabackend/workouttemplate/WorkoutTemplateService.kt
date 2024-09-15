package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.exercise.ExerciseService
import com.flohrauer.endurabackend.workouttemplate.dto.AddExerciseRequest
import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.UpdateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.WorkoutTemplateResponse
import com.flohrauer.endurabackend.workouttemplate.exception.ExerciseAlreadyInWorkoutTemplateException
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

    fun create(createWorkoutTemplateRequest: CreateWorkoutTemplateRequest): WorkoutTemplateResponse {
        try {
            val exercises = exerciseService.getAllEntitiesByIds(createWorkoutTemplateRequest.exercisesIds)
            val workoutTemplateEntity = createWorkoutTemplateRequest.toEntity(exercises.toMutableSet())
            val databaseTemplateEntity = workoutTemplateRepository.save(workoutTemplateEntity)

            return databaseTemplateEntity.toResponse()
        } catch (e: DataIntegrityViolationException) {
            throw WorkoutTemplateAlreadyExistsException()
        }
    }

    fun update(id: UUID, updateWorkoutTemplateRequest: UpdateWorkoutTemplateRequest): WorkoutTemplateResponse {
        try {
            val workoutTemplateEntity = getEntityById(id)
            val updatedWorkoutTemplate = workoutTemplateEntity.update(updateWorkoutTemplateRequest)
            val databaseWorkoutTemplate = workoutTemplateRepository.save(updatedWorkoutTemplate)

            return databaseWorkoutTemplate.toResponse()
        } catch (e: DataIntegrityViolationException) {
            throw WorkoutTemplateAlreadyExistsException()
        }
    }

    fun delete(id: UUID) {
        val workoutTemplateEntity = getEntityById(id)
        workoutTemplateRepository.delete(workoutTemplateEntity)
    }

    fun addExercise(id: UUID, addExerciseRequest: AddExerciseRequest): WorkoutTemplateResponse {
        try {
            val workoutTemplateEntity = getEntityById(id)
            val exerciseEntity = exerciseService.getEntityById(addExerciseRequest.id)
            workoutTemplateEntity.exercises.add(exerciseEntity)

            return workoutTemplateRepository.save(workoutTemplateEntity).toResponse()
        } catch (e: DataIntegrityViolationException) {
            throw ExerciseAlreadyInWorkoutTemplateException()
        }
    }

    fun removeExercise(id: UUID, exerciseId: UUID): WorkoutTemplateResponse {
        val workoutTemplateEntity = getEntityById(id)
        val exerciseEntity = exerciseService.getEntityById(exerciseId)
        workoutTemplateEntity.exercises.remove(exerciseEntity)

        return workoutTemplateRepository.save(workoutTemplateEntity).toResponse()
    }

    private fun getEntityById(id: UUID): WorkoutTemplate {
        return workoutTemplateRepository.findById(id).orElseThrow {
            WorkoutTemplateNotFoundException()
        }
    }
}
