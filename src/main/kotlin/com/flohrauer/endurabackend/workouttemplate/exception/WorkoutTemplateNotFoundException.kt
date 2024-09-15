package com.flohrauer.endurabackend.workouttemplate.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class WorkoutTemplateNotFoundException: CustomException(
    statusCode = HttpStatus.NOT_FOUND,
    message = "Workout template not found."
)
