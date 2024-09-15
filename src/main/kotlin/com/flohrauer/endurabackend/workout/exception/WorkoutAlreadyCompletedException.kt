package com.flohrauer.endurabackend.workout.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class WorkoutAlreadyCompletedException: CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    message = "Workout has already been completed."
)
