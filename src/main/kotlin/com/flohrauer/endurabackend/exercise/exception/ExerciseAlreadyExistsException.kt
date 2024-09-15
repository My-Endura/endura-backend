package com.flohrauer.endurabackend.exercise.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class ExerciseAlreadyExistsException: CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    message = "Exercise with this name already exists."
)
