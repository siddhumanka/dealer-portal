package com.heycar.dealerportal.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockMultipartFile

internal class ProviderControllerTest {
    private val controller = ProviderController()

    private val file = MockMultipartFile(
            "file",
            "hello.csv",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
    )

    private val jsonBody = "json"
    private val validDealerId = "123"
    private val emptyDealerId = ""

    @Test
    internal fun `should return bad request for empty dealer id, valid csv`() {
        val response: ResponseEntity<String> = controller.uploadCsvListings(emptyDealerId, file)

        assertThat(response.statusCodeValue).isEqualTo(400)
    }

    @Test
    internal fun `should return 201 for valid dealer id, valid csv`() {
        val response: ResponseEntity<String> = controller.uploadCsvListings(validDealerId, file)

        assertThat(response.statusCodeValue).isEqualTo(204)
    }


    @Test
    internal fun `should return bad request for empty dealer id, valid json`() {
        val response: ResponseEntity<String> = controller.uploadListings(emptyDealerId, jsonBody)

        assertThat(response.statusCodeValue).isEqualTo(400)
    }

    @Test
    internal fun `should return 201 for valid dealer id, valid json`() {
        val response: ResponseEntity<String> = controller.uploadListings(validDealerId, jsonBody)

        assertThat(response.statusCodeValue).isEqualTo(204)
    }
}