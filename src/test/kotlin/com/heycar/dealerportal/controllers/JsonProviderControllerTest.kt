package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.services.ProviderService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.only
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.ResponseEntity

@ExtendWith(MockitoExtension::class)
internal class JsonProviderControllerTest{

    @Mock
    private lateinit var providerService: ProviderService

    @InjectMocks
    private lateinit var controller : JsonProviderController

    private val jsonBody = listOf(Listing(code = "a",
            make = "Audi",
            model = "r8",
            kW = "power",
            year = "2107",
            color = "black",
            price = "29030")
    )
    private val validDealerId = "123"
    private val emptyDealerId = ""

    @Test
    internal fun `should return bad request for empty dealer id, valid json`() {
        val response: ResponseEntity<String> = controller.uploadListings(emptyDealerId, jsonBody)

        Assertions.assertThat(response.statusCodeValue).isEqualTo(400)
    }

    @Test
    internal fun `should return 201 for valid dealer id, valid json`() {
        val response: ResponseEntity<String> = controller.uploadListings(validDealerId, jsonBody)

        Assertions.assertThat(response.statusCodeValue).isEqualTo(204)
        verify(providerService, only()).save(validDealerId, jsonBody)
    }

}