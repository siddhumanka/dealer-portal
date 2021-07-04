package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.models.SearchFilter
import com.heycar.dealerportal.services.ProviderService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(private val providerService: ProviderService) {

    @GetMapping("/search")
    fun searchListings(@RequestParam("make") make: String? = null,
                       @RequestParam("model") model: String? = null,
                       @RequestParam("year") year: String? = null,
                       @RequestParam("color") color: String? = null): List<Listing> {

        return providerService.getAllListings(SearchFilter(make, model, year, color))
    }
}