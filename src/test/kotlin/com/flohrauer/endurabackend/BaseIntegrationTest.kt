package com.flohrauer.endurabackend

import com.flohrauer.endurabackend.core.toJson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.test.web.servlet.*
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

    @Autowired
    private lateinit var mockMvc: MockMvc

    @AfterEach
    fun cleanup() {
        val populator = ResourceDatabasePopulator()
        populator.addScripts(ClassPathResource("/sql/cleanup.sql"))
        populator.execute(dataSource)
    }

    protected fun get(url: String, params: Map<String, List<String>>? = null): ResultActionsDsl {
        return mockMvc.get(url) {
            mapParams(params)
        }
    }

    protected fun post(url: String, body: Any? = null): ResultActionsDsl {
        return mockMvc.post(url) {
            content = body?.toJson()
            contentType = MediaType.APPLICATION_JSON
        }
    }

    private fun MockHttpServletRequestDsl.mapParams(params: Map<String, List<String>>? = null) {
        val paramBuilders = params?.flatMap { (key, values) ->
            values.map { Pair(key, it) }
        }

        paramBuilders?.forEach { (key, value) -> param(key, value) }
    }
}
