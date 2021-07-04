package com.heycar.dealerportal.repositories

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.testhelpers.TestHelper.createListing
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
        val listing = createListing()
        listingRepository.save(dealerId, listOf(listing))

        val listingForDealer = listingRepository.getAllListings()[0]
        assertThat(listingForDealer).isEqualTo(listing)
    }

    @Test
    internal fun `should update a listing in store if listing with same dealerId and code already exists`() {
        val dealerId = "23"
        val listing1 = createListing()
        val listing2 = Listing(code = "a",
                make = "Audi",
                model = "r9",
                kW = "990",
                year = "2017",
                color = "black",
                price = "29030")

        listingRepository.save(dealerId, listOf(listing1, listing2))

        val listingForDealer = listingRepository.getAllListings()[0]
        assertThat(listingForDealer).isEqualTo(listing2)
    }
}