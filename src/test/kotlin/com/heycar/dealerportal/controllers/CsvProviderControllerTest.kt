package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.helpers.CsvHelper
import com.heycar.dealerportal.services.ProviderService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.mock.web.MockMultipartFile

@ExtendWith(MockitoExtension::class)
internal class CsvProviderControllerTest {

    @Mock
    private lateinit var service: ProviderService

    @InjectMocks
    private lateinit var controller: CsvProviderController

    private val file = MockMultipartFile(
            "file",
            "hello.csv",
            MediaType.TEXT_PLAIN_VALUE,
            "Hello, World!".toByteArray()
    )

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
        verify(service, only()).save(validDealerId, CsvHelper.csvToListings(file.inputStream))
    }


}