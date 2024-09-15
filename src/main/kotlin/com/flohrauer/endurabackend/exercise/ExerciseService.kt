package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.exercise.dto.ExerciseResponse
import com.flohrauer.endurabackend.exercise.exception.ExerciseNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExerciseService(private val exerciseRepository: ExerciseRepository) {
    fun getAll(): List<ExerciseResponse> {
        val exercises = exerciseRepository.findAll()
        return exercises.map { it.toResponse() }
    }

    fun getById(id: UUID): ExerciseResponse {
        return getEntityById(id).toResponse()
    }

    private fun getEntityById(id: UUID): Exercise {
        return exerciseRepository.findById(id).orElseThrow {
            ExerciseNotFoundException()
        }
    }
}
