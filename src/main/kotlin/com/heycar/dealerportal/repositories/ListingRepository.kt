package com.heycar.dealerportal.repositories

import com.heycar.dealerportal.models.Listing
import org.springframework.stereotype.Repository

@Repository
class ListingRepository {
    private val store = hashMapOf<String, List<Listing>>()

    fun save(dealerId: String, listings: List<Listing>) {
        store.putIfAbsent(dealerId, listings)
    }

    fun getAllListings(): List<Listing> {
        return store.values.flatten()
    }
}