package com.flohrauer.endurabackend.workouttemplate

import com.flohrauer.endurabackend.BaseIntegrationTest
import com.flohrauer.endurabackend.workouttemplate.dto.AddExerciseRequest
import com.flohrauer.endurabackend.workouttemplate.dto.CreateWorkoutTemplateRequest
import com.flohrauer.endurabackend.workouttemplate.dto.UpdateWorkoutTemplateRequest
import org.springframework.test.context.jdbc.Sql
import java.util.*
import kotlin.test.Test

class WorkoutTemplateControllerTest: BaseIntegrationTest() {

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should get all workout template from database`() {
        get("/workout-template").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value (2) }

            jsonPath("$[0].name") { value("Armtraining Montag") }
            jsonPath("$[0].description") { value("Armtraining für jeden Montag") }
            jsonPath("$[0].lastCompleted") { value(null) }
            jsonPath("$[0].exercises.size()") { value(2) }
            jsonPath("$[0].exercises[0].name") { value("Klimmzüge") }
            jsonPath("$[0].exercises[1].name") { value("Liegestütze") }

            jsonPath("$[1].name") { value("Brusttag Dienstag") }
            jsonPath("$[1].description") { value("Brusttraining für Dienstag") }
            jsonPath("$[1].lastCompleted") { value("2024-09-15T12:00:00") }
            jsonPath("$[1].exercises.size()") { value(1) }
            jsonPath("$[1].exercises[0].name") { value("Liegestütze") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should get workout template by id from database`() {
        val workoutTemplateId = UUID.fromString("ec31dbdd-4e70-4019-b5ec-a524d8521b9f")

        get("/workout-template/$workoutTemplateId").andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Armtraining Montag") }
            jsonPath("$.description") { value("Armtraining für jeden Montag") }
            jsonPath("$.lastCompleted") { value(null) }
            jsonPath("$.exercises.size()") { value(2) }
            jsonPath("$.exercises[0].name") { value("Klimmzüge") }
            jsonPath("$.exercises[1].name") { value("Liegestütze") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should throw not found if workout template with id was not found in database`() {
        val workoutTemplateId = UUID.fromString("f9b8ccf4-b00d-49f0-a49b-4a8db8e9844d")

        get("/workout-template/$workoutTemplateId").andExpect {
            status { isNotFound() }
            jsonPath("$.message") { value("Workout template not found.") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should create new workout template with exercises`() {
        val createWorkoutTemplateRequest = CreateWorkoutTemplateRequest(
            name = "Mittwoch Sport",
            description = "Sport am Mittwoch",
            exercisesIds = listOf(
                UUID.fromString("3b092329-89c6-4bdb-bba7-24341dcf5ba1"), // Kniebeugen
                UUID.fromString("481b1e54-d834-43b8-bb55-ff10bf21d42c"), // Plank
                UUID.fromString("3122e162-032f-4cc0-8389-89aa794c3896"), // Bauchpressen
            )
        )

        post("/workout-template", createWorkoutTemplateRequest).andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Mittwoch Sport") }
            jsonPath("$.description") { value("Sport am Mittwoch") }
            jsonPath("$.lastCompleted") { value(null) }
            jsonPath("$.exercises.size()") { value(3) }
            jsonPath("$.exercises[0].name") { value("Bauchpressen") }
            jsonPath("$.exercises[1].name") { value("Kniebeugen") }
            jsonPath("$.exercises[2].name") { value("Plank") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should update existing workout template`() {
        val workoutTemplateId = UUID.fromString("ec31dbdd-4e70-4019-b5ec-a524d8521b9f")
        val updateWorkoutTemplateRequest = UpdateWorkoutTemplateRequest(
            name = "Donnerstag Sport",
            description = "Sport am Donnerstag"
        )

        put("/workout-template/$workoutTemplateId", updateWorkoutTemplateRequest).andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Donnerstag Sport") }
            jsonPath("$.description") { value("Sport am Donnerstag") }
            jsonPath("$.lastCompleted") { value(null) }
            jsonPath("$.exercises.size()") { value(2) }
            jsonPath("$.exercises[0].name") { value("Klimmzüge") }
            jsonPath("$.exercises[1].name") { value("Liegestütze") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should delete workout template without deleting exercises`() {
        val workoutTemplateId = UUID.fromString("36d629a7-5ad2-4aa0-8898-01a106424147") // Brusttag Dienstag

        delete("/workout-template/$workoutTemplateId").andExpect {
            status { isNoContent() }
        }

        get("/workout-template/$workoutTemplateId").andExpect {
            status { isNotFound() }
            jsonPath("$.message") { value("Workout template not found.") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should add exercise to workout template`() {
        val workoutTemplateId = UUID.fromString("ec31dbdd-4e70-4019-b5ec-a524d8521b9f") // Armtraining Montag
        val exerciseId = UUID.fromString("3b092329-89c6-4bdb-bba7-24341dcf5ba1") // Kniebeugen
        val addExerciseRequest = AddExerciseRequest(exerciseId)

        post("/workout-template/$workoutTemplateId/exercise", addExerciseRequest).andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Armtraining Montag") }
            jsonPath("$.description") { value("Armtraining für jeden Montag") }
            jsonPath("$.lastCompleted") { value(null) }

            jsonPath("$.exercises.size()") { value(3) }
            jsonPath("$.exercises[0].name") { value("Klimmzüge") }
            jsonPath("$.exercises[1].name") { value("Kniebeugen") }
            jsonPath("$.exercises[2].name") { value("Liegestütze") }
        }
    }

    @Test
    @Sql("/sql/workouttemplate/setup_workout_template.sql")
    fun `should remove exercise from workout template`() {
        val workoutTemplateId = UUID.fromString("ec31dbdd-4e70-4019-b5ec-a524d8521b9f") // Armtraining Montag
        val exerciseId = UUID.fromString("a752da83-1281-4eba-9298-da041f249bb9") // Liegestütze

        delete("/workout-template/$workoutTemplateId/exercise/$exerciseId").andExpect {
            status { isNoContent() }
        }

        get("/workout-template/$workoutTemplateId").andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Armtraining Montag") }
            jsonPath("$.description") { value("Armtraining für jeden Montag") }
            jsonPath("$.lastCompleted") { value(null) }

            jsonPath("$.exercises.size()") { value(1) }
            jsonPath("$.exercises[0].name") { value("Klimmzüge") }
        }
    }
}
