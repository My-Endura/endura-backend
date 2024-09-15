package com.flohrauer.endurabackend.core.dto

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val status: HttpStatus,
    val message: String
)
