package com.flohrauer.endurabackend.workout

import com.flohrauer.endurabackend.workout.dto.CreateWorkoutRequest
import com.flohrauer.endurabackend.workout.dto.WorkoutResponse
import com.flohrauer.endurabackend.workout.exception.WorkoutAlreadyCompletedException
import com.flohrauer.endurabackend.workout.exception.WorkoutNotFoundException
import com.flohrauer.endurabackend.workouttemplate.WorkoutTemplateService
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository,
    private val workoutTemplateService: WorkoutTemplateService
) {

    fun getAll(): List<WorkoutResponse> {
        val sort = Sort
            .by(Sort.Order.by(Workout::completedAt.name).nullsFirst())
            .descending()

        val workoutEntities = workoutRepository.findAll(sort)
        return workoutEntities.map { it.toResponse() }
    }

    fun getById(id: UUID): WorkoutResponse {
        return getEntityById(id).toResponse()
    }

    fun create(createWorkoutRequest: CreateWorkoutRequest): WorkoutResponse {
        val workoutTemplate = createWorkoutRequest.workoutTemplateId?.let {
            workoutTemplateService.getEntityById(it)
        }

        val workoutEntity = createWorkoutRequest.toEntity(workoutTemplate)
        val databaseWorkout = workoutRepository.save(workoutEntity)

        // todo get workout template and copy exercises

        return databaseWorkout.toResponse()
    }

    fun complete(id: UUID): WorkoutResponse {
        val workoutEntity = getEntityById(id)

        if(workoutEntity.isCompleted()) {
            throw WorkoutAlreadyCompletedException()
        }

        workoutEntity.completedAt = LocalDateTime.now()
        workoutEntity.duration = Duration
            .between(workoutEntity.startedAt, LocalDateTime.now())
            .toMinutes()
        // todo calculate total workout weights

        return workoutRepository.save(workoutEntity).toResponse()
    }

    fun getEntityById(id: UUID): Workout {
        return workoutRepository.findById(id).orElseThrow {
            WorkoutNotFoundException()
        }
    }
}
