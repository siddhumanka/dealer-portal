package com.heycar.dealerportal.repositories

import com.heycar.dealerportal.models.Listing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ListingRepositoryTest {

    private val listingRepository = ListingRepository()

    @Test
    internal fun `should return empty for no entry in store`() {
        val listingForDealer = listingRepository.getAllListings()

        assertThat(listingForDealer).isEmpty()
    }

    @Test
    internal fun `should save a Listing in store`() {
        val dealerId = "23"
        val listing = Listing(code = "a",
                make = "Audi",
                model = "r8",
                kW = "power",
                year = "2107",
                color = "black",
                price = "29030")

        listingRepository.save(dealerId, listOf(listing))

        val listingForDealer = listingRepository.getAllListings()[0]
        assertThat(listingForDealer).isEqualTo(listing)
    }
}