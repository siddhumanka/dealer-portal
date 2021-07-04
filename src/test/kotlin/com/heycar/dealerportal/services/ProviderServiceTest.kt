package com.heycar.dealerportal.services

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.repositories.ListingRepository
import com.heycar.dealerportal.testhelpers.TestHelper.createListing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class ProviderServiceTest{

    @Mock
    private lateinit var listingRepository: ListingRepository

    @InjectMocks
    private lateinit var providerService: ProviderService

    @Test
    internal fun `should save data from file`() {
        val dealerID = "1"

        providerService.save(dealerID, listOf())

        verify(listingRepository, only()).save(dealerID, listOf())
    }

    @Test
    internal fun `should get all listings from the store`() {
        val expectedListings = listOf(createListing())
        `when`(listingRepository.getAllListings()).thenReturn(expectedListings)

        val actualListings: List<Listing> = providerService.getAllListings()

        assertThat(actualListings).isEqualTo(expectedListings)
    }
}