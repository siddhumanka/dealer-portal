package com.heycar.dealerportal.integration

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.services.ProviderService
import com.heycar.dealerportal.testhelpers.TestHelper.createListing
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
internal class SearchIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var providerService: ProviderService

    @BeforeEach
    internal fun setUp() {
        providerService.save("1", listOf(
                createListing(),
                Listing(code = "b",
                        make = "Volkwagen",
                        model = "m 10",
                        kW = "200",
                        year = "2015",
                        color = "white",
                        price = "89000")))

        providerService.save("2", listOf(
                Listing(code = "a",
                make = "Skoda",
                model = "octavia",
                kW = "890",
                year = "2020",
                color = "white",
                price = "19800")))
    }


    @Test
    internal fun `should return all vehicle listings if no parameters are specified`() {
        val request = get("/search")

        val response = mockMvc.perform(request)

        response.andExpect(status().isOk)
                .andExpect(jsonPath("$[0].code").value("a"))
                .andExpect(jsonPath("$[0].make").value("Audi"))
                .andExpect(jsonPath("$[1].code").value("b"))
                .andExpect(jsonPath("$[1].make").value("Volkwagen"))
                .andExpect(jsonPath("$[2].code").value("a"))
                .andExpect(jsonPath("$[2].make").value("Skoda"))
    }
}