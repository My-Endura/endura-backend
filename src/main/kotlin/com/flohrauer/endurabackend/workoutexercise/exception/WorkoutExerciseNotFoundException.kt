package com.flohrauer.endurabackend.workoutexercise.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class WorkoutExerciseNotFoundException: CustomException(
    statusCode = HttpStatus.NOT_FOUND,
    message = "Workout exercise was not found."
)
