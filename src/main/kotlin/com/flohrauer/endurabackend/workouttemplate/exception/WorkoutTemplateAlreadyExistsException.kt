package com.flohrauer.endurabackend.workouttemplate.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class WorkoutTemplateAlreadyExistsException: CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    message = "Workout template with this name already exists."
)
