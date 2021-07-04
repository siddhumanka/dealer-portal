package com.heycar.dealerportal.integration

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.models.SearchFilter
import com.heycar.dealerportal.services.ProviderService
import com.heycar.dealerportal.testhelpers.TestHelper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class UpdateIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var providerService: ProviderService

    @BeforeEach
    internal fun setUp() {
        providerService.run {
            save("1", listOf(
                TestHelper.createListing(),
                    Listing(code = "b",
                            make = "Volkwagen",
                            model = "m 10",
                            kW = "200",
                            year = "2015",
                            color = "white",
                            price = "89000")))

            save("2", listOf(
                    Listing(code = "a",
                            make = "Skoda",
                            model = "octavia",
                            kW = "890",
                            year = "2020",
                            color = "white",
                            price = "19800")))
        }
    }

    @Test
    internal fun `should update the store if same dealer posts data with same code`() {
        val dealerID = "2"
        val jsonBody = """
            [
                {
                    "code": "a",
                    "make": "Skoda",
                    "model": "megane",
                    "kW": 245,
                    "year": 2015,
                    "color": "red",
                    "price": 13990
                }
            ]
        """.trimIndent()
        val request = MockMvcRequestBuilders.post("/dealers/$dealerID/vehicle-listings")
                .content(jsonBody)
                .contentType("application/json")

        mockMvc.perform(request)

        val foundListing = providerService.getAllListings(SearchFilter()).filter { it.code == "a" && it.make == "Skoda" }.first()
        assertThat(foundListing.code).isEqualTo("a")
        assertThat(foundListing.make).isEqualTo("Skoda")
        assertThat(foundListing.model).isEqualTo("megane")
        assertThat(foundListing.kW).isEqualTo("245")
        assertThat(foundListing.year).isEqualTo("2015")
        assertThat(foundListing.color).isEqualTo("red")
        assertThat(foundListing.price).isEqualTo("13990")
    }
}