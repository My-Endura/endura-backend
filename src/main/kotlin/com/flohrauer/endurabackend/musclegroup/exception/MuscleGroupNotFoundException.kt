package com.flohrauer.endurabackend.musclegroup.exception

import com.flohrauer.endurabackend.core.CustomException
import org.springframework.http.HttpStatus

class MuscleGroupNotFoundException: CustomException(
    statusCode = HttpStatus.NOT_FOUND,
    message = "Muscle group not found."
)
