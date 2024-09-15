package com.flohrauer.endurabackend.core

import com.google.gson.GsonBuilder
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import java.time.Duration
import java.time.Instant

fun Any.toJson(): String {
    return GsonBuilder()
        .setPrettyPrinting()
        .create()
        .toJson(this)
}

fun isCloseToNow(toleranceSeconds: Long = 5): TypeSafeMatcher<String> {
    return object : TypeSafeMatcher<String>() {
        override fun describeTo(description: Description) {
            description.appendText("a value close to now")
        }

        override fun matchesSafely(actual: String): Boolean {
            val actualInstant = Instant.parse(actual)
            val now = Instant.now()
            val lowerBound = now.minus(Duration.ofSeconds(toleranceSeconds))
            val upperBound = now.plus(Duration.ofSeconds(toleranceSeconds))

            return !actualInstant.isBefore(lowerBound) && !actualInstant.isAfter(upperBound)
        }
    }
}
