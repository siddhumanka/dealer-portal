package com.heycar.dealerportal.integration

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.util.ResourceUtils

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class UploadIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    internal fun `should return NOT_FOUND for a non existing url`() {
        val request = get("/bad-url")

        val response = mockMvc.perform(request)

        response.andExpect(status().isNotFound)
    }

    @Test
    internal fun `should return OK for a valid csv upload`() {
        val dealerID = "1"
        val testFileName = "test-dealer-listings.csv"
        val file = ResourceUtils.getFile("classpath:$testFileName")
        val multipartFile = MockMultipartFile(
                "file",
                testFileName,
                MediaType.TEXT_PLAIN_VALUE,
                file.readBytes()
        )

        val response = mockMvc.perform(multipart("/upload-csv/$dealerID")
                .file(multipartFile))

        response.andExpect(status().isOk)
    }

    @Test
    internal fun `should return OK for a valid json upload`() {
        val dealerID = "1"
        val jsonBody = """
            [
                {
                    "code": "a",
                    "make": "renault",
                    "model": "megane",
                    "kW": 132,
                    "year": 2014,
                    "color": "red",
                    "price": 13990
                }
            ]
        """.trimIndent()
        val request = post("/dealers/$dealerID/vehicle-listings")
                .content(jsonBody)
                .contentType("application/json")

        val response = mockMvc.perform(request)

        response.andExpect(status().isOk)
    }

}