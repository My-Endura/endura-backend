package com.flohrauer.endurabackend.exercise.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class ExerciseNotFoundException: CustomException(
    statusCode = HttpStatus.NOT_FOUND,
    message = "Exercise not found."
)
