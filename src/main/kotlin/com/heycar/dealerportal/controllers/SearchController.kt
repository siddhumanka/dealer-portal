package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.services.ProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(private val providerService: ProviderService) {

    @GetMapping("/search")
    fun searchListings(): List<Listing> {
        return providerService.getAllListings()
    }
}