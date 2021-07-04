package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.models.Listing
import com.heycar.dealerportal.services.ProviderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class JsonProviderController(private val providerService: ProviderService) {

    @PostMapping("/dealers/{dealerId}/vehicle-listings")
    fun uploadListings(@PathVariable("dealerId") dealerId: String,
                       @RequestBody listings: List<Listing>): ResponseEntity<String> {
        if (dealerId.isEmpty()) return ResponseEntity.badRequest().body("bad request")
        providerService.save(dealerId, listings)
        return ResponseEntity.noContent().build()
    }
}