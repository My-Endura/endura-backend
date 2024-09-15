package com.flohrauer.endurabackend

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.testcontainers.junit.jupiter.Testcontainers
import javax.sql.DataSource

@SpringBootTest
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@EnableConfigurationProperties
@AutoConfigureMockMvc
class BaseIntegrationTest {

    @Autowired
    private lateinit var dataSource: DataSource

    @AfterEach
    fun cleanup() {
        val populator = ResourceDatabasePopulator()
        populator.addScripts(ClassPathResource("/sql/cleanup.sql"))
        populator.execute(dataSource)
    }
}
