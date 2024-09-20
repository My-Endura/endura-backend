package com.flohrauer.endurabackend.workout

import com.flohrauer.endurabackend.BaseIntegrationTest
import com.flohrauer.endurabackend.core.isCloseToNow
import com.flohrauer.endurabackend.workout.dto.CreateWorkoutRequest
import org.springframework.test.context.jdbc.Sql
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.test.Test

class WorkoutControllerTest: BaseIntegrationTest() {

    @Test
    @Sql("/sql/workout/setup_workout.sql")
    fun `should get all workouts from database`() {
        get("/workout").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(3) }

            jsonPath("$[0].name") { value("Workout 15.09") }
            jsonPath("$[0].startedAt") { value("2024-09-15T16:00:00") }
            jsonPath("$[0].completedAt") { value(null) }
            jsonPath("$[0].duration") { value(null) }
            jsonPath("$[0].totalWeight") { value(null) }

            jsonPath("$[1].name") { value("Workout 14.09") }
            jsonPath("$[1].startedAt") { value("2024-09-14T13:00:00") }
            jsonPath("$[1].completedAt") { value("2024-09-14T13:52:00") }
            jsonPath("$[1].duration") { value(3120) }
            jsonPath("$[1].totalWeight") { value(2340) }

            jsonPath("$[2].name") { value("Workout 13.09") }
            jsonPath("$[2].startedAt") { value("2024-09-13T20:00:00") }
            jsonPath("$[2].completedAt") { value("2024-09-13T22:00:00") }
            jsonPath("$[2].duration") { value(7200) }
            jsonPath("$[2].totalWeight") { value(7532) }
        }
    }

    @Test
    @Sql("/sql/workout/setup_workout.sql")
    fun `should get workout by id from database`() {
        val workoutId = UUID.fromString("3fc411fa-c0c6-49dc-af11-917440c8e584")

        get("/workout/$workoutId").andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Workout 14.09") }
            jsonPath("$.startedAt") { value("2024-09-14T13:00:00") }
            jsonPath("$.completedAt") { value("2024-09-14T13:52:00") }
            jsonPath("$.duration") { value(3120) }
            jsonPath("$.totalWeight") { value(2340) }
        }
    }

    @Test
    @Sql("/sql/workout/setup_workout.sql")
    fun `should throw not found if workout with id was not found in database`() {
        val workoutId = UUID.fromString("dd73e323-eb3f-4f02-bc2a-860fbbe9bf74")

        get("/workout/$workoutId").andExpect {
            status { isNotFound() }
            jsonPath("$.message") { value("Workout not found.") }
        }
    }

    @Test
    fun `should create workout without template`() {
        val createWorkoutRequest = CreateWorkoutRequest(
            name = "Montag Nachmittagstraining",
            workoutTemplateId = null
        )

        post("/workout", createWorkoutRequest).andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Montag Nachmittagstraining") }
            jsonPath("$.startedAt") { isCloseToNow() }
            jsonPath("$.completedAt") { value(null) }
            jsonPath("$.duration") { value(null) }
            jsonPath("$.totalWeight") { value(null) }
        }
    }

    @Test
    @Sql("/sql/workout/setup_workout.sql")
    fun `should complete workout`() {
        val workoutId = UUID.fromString("eee6a43d-b583-47f9-8a66-88761733d1bc")

        put("/workout/$workoutId/complete").andExpect {
            val expectedDuration = Duration
                .between(LocalDateTime.parse("2024-09-15T16:00:00"), LocalDateTime.now())
                .toMinutes()

            status { isOk() }
            jsonPath("$.name") { value("Workout 15.09") }
            jsonPath("$.startedAt") { value("2024-09-15T16:00:00") }
            jsonPath("$.completedAt") { isCloseToNow() }
            jsonPath("$.duration") { value(expectedDuration) }
            jsonPath("$.totalWeight") { value(null) } // todo dont forget
        }
    }

    @Test
    @Sql("/sql/workout/setup_workout.sql")
    fun `should throw already completed if completedAt is not null`() {
        val workoutId = UUID.fromString("d7b61782-54f7-4032-908e-b3d6e9ceed64")

        put("/workout/$workoutId/complete").andExpect {
            status { isBadRequest() }
            jsonPath("$.message") { value("Workout has already been completed.") }
        }
    }
}
