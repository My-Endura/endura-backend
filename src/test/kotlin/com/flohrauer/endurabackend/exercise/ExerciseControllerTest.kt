package com.flohrauer.endurabackend.exercise

import com.flohrauer.endurabackend.BaseIntegrationTest
import com.flohrauer.endurabackend.exercise.dto.CreateExerciseRequest
import org.springframework.test.context.jdbc.Sql
import java.util.*
import kotlin.test.Test

class ExerciseControllerTest: BaseIntegrationTest() {

    @Test
    @Sql("/sql/exercise/setup_exercise.sql")
    fun `should get all exercises from database`() {
        get("/exercise").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(5) }

            jsonPath("$[0].name") { value("Kniebeugen") }
            jsonPath("$[0].description") { value("Eine Übung für die Beine") }
            jsonPath("$[0].instructions") { value("Stell dich mit den Füßen schulterbreit auseinander und beuge die Knie, als ob du dich hinsetzen würdest.") }
            jsonPath("$[0].muscleGroups.size()") { value(1) }
            jsonPath("$[0].muscleGroups[0].name") { value("Oberschenkel") }

            jsonPath("$[1].name") { value("Liegestütze") }
            jsonPath("$[1].description") { value("Eine Übung für die Brust und Arme") }
            jsonPath("$[1].instructions") { value("Leg dich auf den Bauch, stütze dich auf die Hände und die Zehen, und senke deinen Körper bis fast zum Boden.") }
            jsonPath("$[1].muscleGroups.size()") { value(2) }
            jsonPath("$[1].muscleGroups[0].name") { value("Arme") }
            jsonPath("$[1].muscleGroups[1].name") { value("Brust") }

            jsonPath("$[2].name") { value("Bauchpressen") }
            jsonPath("$[2].description") { value("Eine Übung für die Bauchmuskeln") }
            jsonPath("$[2].instructions") { value("Lieg auf dem Rücken, die Beine angewinkelt, und hebe den Oberkörper an, als ob du dich aufsetzen würdest.") }
            jsonPath("$[2].muscleGroups") { isEmpty() }

            jsonPath("$[3].name") { value("Klimmzüge") }
            jsonPath("$[3].description") { value("Eine Übung für den oberen Rücken") }
            jsonPath("$[3].instructions") { value("Häng dich an eine Stange und zieh deinen Körper nach oben, bis dein Kinn über der Stange ist.") }
            jsonPath("$[3].muscleGroups.size()") { value(2) }
            jsonPath("$[3].muscleGroups[0].name") { value("Arme") }
            jsonPath("$[3].muscleGroups[1].name") { value("Rücken") }

            jsonPath("$[4].name") { value("Plank") }
            jsonPath("$[4].description") { value("Eine Übung für die Körpermitte") }
            jsonPath("$[4].instructions") { value("Stütz dich auf die Unterarme und die Zehen, halte deinen Körper in einer geraden Linie.") }
            jsonPath("$[4].muscleGroups") { isEmpty() }
        }
    }

    @Test
    @Sql("/sql/exercise/setup_exercise.sql")
    fun `should get exercise by id from database`() {
        val exerciseId = UUID.fromString("a752da83-1281-4eba-9298-da041f249bb9")
        get("/exercise/$exerciseId").andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Liegestütze") }
            jsonPath("$.description") { value("Eine Übung für die Brust und Arme") }
            jsonPath("$.instructions") { value("Leg dich auf den Bauch, stütze dich auf die Hände und die Zehen, und senke deinen Körper bis fast zum Boden.") }
            jsonPath("$.muscleGroups.size()") { value(2) }
            jsonPath("$.muscleGroups[0].name") { value("Arme") }
            jsonPath("$.muscleGroups[1].name") { value("Brust") }
        }
    }

    @Test
    @Sql("/sql/exercise/setup_exercise.sql")
    fun `should throw exercise not found if id is not found in database`() {
        val exerciseId = UUID.fromString("b6289aba-6d7f-45df-82cf-29529f8ae4f1")

        get("/exercise/$exerciseId").andExpect {
            status { isNotFound() }
            jsonPath("$.message") { value("Exercise not found.") }
        }
    }

    @Test
    @Sql("/sql/exercise/setup_exercise.sql")
    fun `should create new exercise in database`() {
        val createExerciseRequest = CreateExerciseRequest(
            name = "Bench Press",
            description = "Exercise for building muscle",
            instructions = null,
            muscleGroupsIds = listOf(
                UUID.fromString("e56d0340-daa6-4523-bb61-dd047eefa340"), // Brust
                UUID.fromString("b69b80d2-fe63-4fde-ad54-7edbbab55236"), // Rücken
            )
        )

        post("/exercise", createExerciseRequest).andExpect {
            status { isCreated() }
            jsonPath("$.name") { value("Bench Press") }
            jsonPath("$.description") { value("Exercise for building muscle") }
            jsonPath("$.instructions") { value(null) }
            jsonPath("$.muscleGroups.size()") { value(2) }
            jsonPath("$.muscleGroups[0].name") { value("Brust") }
            jsonPath("$.muscleGroups[1].name") { value("Rücken") }
        }
    }

    @Test
    @Sql("/sql/exercise/setup_exercise.sql")
    fun `should throw exercise already exists if name is not unique`() {
        val createExerciseRequest = CreateExerciseRequest(
            name = "Kniebeugen",
            description = null,
            instructions = null,
            muscleGroupsIds = emptyList()
        )

        post("/exercise", createExerciseRequest).andExpect {
            status { isBadRequest() }
            jsonPath("$.message") { value("Exercise with this name already exists.") }
        }
    }
}
