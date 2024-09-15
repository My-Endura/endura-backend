package com.flohrauer.endurabackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EnduraBackendApplication

fun main(args: Array<String>) {
    runApplication<EnduraBackendApplication>(*args)
}
