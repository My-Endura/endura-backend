package com.flohrauer.endurabackend.workouttemplate.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class ExerciseAlreadyInWorkoutTemplateException: CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    message = "Exercise is already in workout template"
)
