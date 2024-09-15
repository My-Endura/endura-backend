package com.flohrauer.endurabackend.core

import org.springframework.http.HttpStatus

abstract class CustomException (
    val statusCode: HttpStatus,
    override val message: String
): RuntimeException(message)
