package com.flohrauer.endurabackend.core

import com.flohrauer.endurabackend.core.dto.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(CustomException::class)
    @ResponseBody
    fun handleCustomException(e: CustomException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(e.statusCode, e.message), e.statusCode)
    }
}
