package com.heycar.dealerportal.repositories

import com.heycar.dealerportal.models.Listing
import org.springframework.stereotype.Repository

@Repository
class ListingRepository {
    private val store = hashMapOf<Pair<String, String>, Listing>()

    fun save(dealerId: String, listings: List<Listing>) {
        listings.forEach { store[Pair(dealerId, it.code)] = it }
    }

    fun getAllListings(): List<Listing> {
        return store.values.toList()
    }
}