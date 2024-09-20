package com.flohrauer.endurabackend.workout.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class WorkoutNotFoundException: CustomException(
    statusCode = HttpStatus.NOT_FOUND,
    message = "Workout not found."
)
