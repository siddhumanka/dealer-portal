package com.heycar.dealerportal.services

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.models.SearchFilter
import com.heycar.dealerportal.repositories.ListingRepository
import org.springframework.stereotype.Service

@Service
class ProviderService(private val repository: ListingRepository) {

    fun save(dealerID: String, listings: List<Listing>) {
        repository.save(dealerID, listings)
    }

    fun getAllListings(request: SearchFilter): List<Listing> {
        return repository.getAllListings()
    }

}