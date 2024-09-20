package com.flohrauer.endurabackend.workoutexercise

import com.flohrauer.endurabackend.BaseIntegrationTest
import com.flohrauer.endurabackend.workoutexercise.dto.CreateWorkoutExerciseRequest
import org.springframework.test.context.jdbc.Sql
import java.util.*
import kotlin.test.Test

class WorkoutExerciseControllerTest: BaseIntegrationTest() {

    @Test
    @Sql("/sql/workoutexercise/setup_workout_exercise.sql")
    fun `should get all workout exercises by workout id`() {
        get("/workout/d7b61782-54f7-4032-908e-b3d6e9ceed64/exercise").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(3) }

            jsonPath("$[0].exercise.name") { value("Kniebeugen") }
            jsonPath("$[0].exercise.description") { value("Eine Übung für die Beine") }
            jsonPath("$[0].exercise.instructions") { value("Stell dich mit den Füßen schulterbreit auseinander und beuge die Knie, als ob du dich hinsetzen würdest.") }
            jsonPath("$[0].exercise.muscleGroups.size()") { value(1) }
            jsonPath("$[0].exercise.muscleGroups[0].name") { value("Oberschenkel") }

            jsonPath("$[1].exercise.name") { value("Klimmzüge") }
            jsonPath("$[1].exercise.description") { value("Eine Übung für den oberen Rücken") }
            jsonPath("$[1].exercise.instructions") { value("Häng dich an eine Stange und zieh deinen Körper nach oben, bis dein Kinn über der Stange ist.") }
            jsonPath("$[1].exercise.muscleGroups.size()") { value(2) }
            jsonPath("$[1].exercise.muscleGroups[0].name") { value("Arme") }
            jsonPath("$[1].exercise.muscleGroups[1].name") { value("Rücken") }

            jsonPath("$[2].exercise.name") { value("Plank") }
            jsonPath("$[2].exercise.description") { value("Eine Übung für die Körpermitte") }
            jsonPath("$[2].exercise.instructions") { value("Stütz dich auf die Unterarme und die Zehen, halte deinen Körper in einer geraden Linie.") }
            jsonPath("$[2].exercise.muscleGroups") { isEmpty() }
        }

        get("/workout/3fc411fa-c0c6-49dc-af11-917440c8e584/exercise").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(2) }

            jsonPath("$[0].exercise.name") { value("Plank") }
            jsonPath("$[0].exercise.description") { value("Eine Übung für die Körpermitte") }
            jsonPath("$[0].exercise.instructions") { value("Stütz dich auf die Unterarme und die Zehen, halte deinen Körper in einer geraden Linie.") }
            jsonPath("$[0].exercise.muscleGroups") { isEmpty() }

            jsonPath("$[1].exercise.name") { value("Bauchpressen") }
            jsonPath("$[1].exercise.description") { value("Eine Übung für die Bauchmuskeln") }
            jsonPath("$[1].exercise.instructions") { value("Lieg auf dem Rücken, die Beine angewinkelt, und hebe den Oberkörper an, als ob du dich aufsetzen würdest.") }
            jsonPath("$[1].exercise.muscleGroups") { isEmpty() }
        }

        get("/workout/eee6a43d-b583-47f9-8a66-88761733d1bc/exercise").andExpect {
            status { isOk() }
            jsonPath("$") { isEmpty() }
        }
    }

    @Test
    @Sql("/sql/workoutexercise/setup_workout_exercise.sql")
    fun `should add new exercise to workout`() {
        val workoutId = UUID.fromString("eee6a43d-b583-47f9-8a66-88761733d1bc")
        val exerciseId = UUID.fromString("481b1e54-d834-43b8-bb55-ff10bf21d42c") // Plank
        val addWorkoutExerciseRequest = CreateWorkoutExerciseRequest(exerciseId)

        post("/workout/$workoutId/exercise", addWorkoutExerciseRequest).andExpect {
            status { isCreated() }
        }

        get("/workout/$workoutId/exercise").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(1) }

            jsonPath("$[0].exercise.name") { value("Plank") }
            jsonPath("$[0].exercise.description") { value("Eine Übung für die Körpermitte") }
            jsonPath("$[0].exercise.instructions") { value("Stütz dich auf die Unterarme und die Zehen, halte deinen Körper in einer geraden Linie.") }
            jsonPath("$[0].exercise.muscleGroups") { isEmpty() }
        }
    }

    @Test
    @Sql("/sql/workoutexercise/setup_workout_exercise.sql")
    fun `should delete exercise from workout`() {
        val workoutId = UUID.fromString("d7b61782-54f7-4032-908e-b3d6e9ceed64")
        val workoutExerciseId = UUID.fromString("ff047f67-0c96-47a3-a236-8099be4b17b4")

        delete("/workout/$workoutId/exercise/$workoutExerciseId").andExpect {
            status { isNoContent() }
        }

        get("/workout/$workoutId/exercise").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(2) }

            jsonPath("$[0].exercise.name") { value("Kniebeugen") }
            jsonPath("$[0].exercise.description") { value("Eine Übung für die Beine") }
            jsonPath("$[0].exercise.instructions") { value("Stell dich mit den Füßen schulterbreit auseinander und beuge die Knie, als ob du dich hinsetzen würdest.") }
            jsonPath("$[0].exercise.muscleGroups.size()") { value(1) }
            jsonPath("$[0].exercise.muscleGroups[0].name") { value("Oberschenkel") }

            jsonPath("$[1].exercise.name") { value("Klimmzüge") }
            jsonPath("$[1].exercise.description") { value("Eine Übung für den oberen Rücken") }
            jsonPath("$[1].exercise.instructions") { value("Häng dich an eine Stange und zieh deinen Körper nach oben, bis dein Kinn über der Stange ist.") }
            jsonPath("$[1].exercise.muscleGroups.size()") { value(2) }
            jsonPath("$[1].exercise.muscleGroups[0].name") { value("Arme") }
            jsonPath("$[1].exercise.muscleGroups[1].name") { value("Rücken") }
        }
    }
}
