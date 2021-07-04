package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.services.ProviderService
import com.heycar.dealerportal.testhelpers.TestHelper.createListing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class SearchControllerTest {

    @Mock
    private lateinit var providerService: ProviderService

    @InjectMocks
    private lateinit var controller: SearchController

    @Test
    internal fun `should  empty for if no listing exists`() {
        val expectedListings = emptyList<Listing>()
        `when`(providerService.getAllListings()).thenReturn(expectedListings)

        val actualListings = controller.searchListings()

        assertThat(actualListings).isEmpty()
    }

    @Test
    internal fun `should return 200 and all listings if no query parameter is provided`() {
        val expectedListings = listOf(createListing())
        `when`(providerService.getAllListings()).thenReturn(expectedListings)

        val actualListings = controller.searchListings()

        assertThat(actualListings).isEqualTo(expectedListings)
    }
}