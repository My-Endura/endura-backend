package com.flohrauer.endurabackend.musclegroup

import com.flohrauer.endurabackend.BaseIntegrationTest
import org.springframework.test.context.jdbc.Sql
import java.util.*
import kotlin.test.Test

class MuscleGroupControllerTest: BaseIntegrationTest() {

    @Test
    @Sql("/sql/musclegroup/setup_musclegroup.sql")
    fun `should get all muscle groups from database`() {
        get("/muscle-group").andExpect {
            status { isOk() }
            jsonPath("$.size()") { value(9) }
            jsonPath("$[0].name") { value("Core") }
            jsonPath("$[1].name") { value("Arme") }
            jsonPath("$[2].name") { value("Rücken") }
            jsonPath("$[3].name") { value("Brust") }
            jsonPath("$[4].name") { value("Beine") }
            jsonPath("$[5].name") { value("Schultern") }
            jsonPath("$[6].name") { value("Cardio") }
            jsonPath("$[7].name") { value("Ganzkörper") }
            jsonPath("$[8].name") { value("Andere") }
        }
    }

    @Test
    @Sql("/sql/musclegroup/setup_musclegroup.sql")
    fun `should get muscle group by id from database`() {
        val muscleGroupId = UUID.fromString("fbaeb698-9c01-40c2-b8f7-ec6036462960")

        get("/muscle-group/${muscleGroupId}").andExpect {
            status { isOk() }
            jsonPath("$.name") { value("Schultern") }
        }
    }

    @Test
    @Sql("/sql/musclegroup/setup_musclegroup.sql")
    fun `should throw muscle group not found if id does not exist in database`() {
        val muscleGroupId = UUID.fromString("70446a22-6623-472d-9901-6b275e47a743")

        get("/muscle-group/${muscleGroupId}").andExpect {
            status { isNotFound() }
            jsonPath("$.message") { value("Muscle group not found.") }
        }
    }
}
