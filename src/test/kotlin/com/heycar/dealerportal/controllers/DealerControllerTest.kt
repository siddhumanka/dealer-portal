package com.heycar.dealerportal.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockMultipartFile

internal class DealerControllerTest {
    private val controller = DealerController()

    private val file = MockMultipartFile(
            "file",
            "hello.csv",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
    )

    @Test
    internal fun `should return bad request for empty dealer id`() {
        val response: ResponseEntity<String> = controller.uploadListing("", file)

        assertThat(response.statusCodeValue).isEqualTo(400)
    }

    @Test
    internal fun `should return 201 for valid dealer id`() {
        val response: ResponseEntity<String> = controller.uploadListing("123", file)

        assertThat(response.statusCodeValue).isEqualTo(204)
    }
}