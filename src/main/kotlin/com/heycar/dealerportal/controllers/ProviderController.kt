package com.heycar.dealerportal.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class ProviderController {

    @PostMapping("/upload-csv/{dealerId}")
    fun uploadCsvListings(@PathVariable("dealerId") dealerId: String,
                          @RequestParam("file") listingFile: MultipartFile): ResponseEntity<String> {
        if (dealerId.isNullOrEmpty()) return ResponseEntity.badRequest().body("bad request")
        return ResponseEntity.noContent().build()
    }

    @PostMapping("/dealers/{dealerId}/vehicle-listings")
    fun uploadListings(@PathVariable("dealerId") dealerId: String,
                       @RequestBody listings: String): ResponseEntity<String> {
        if (dealerId.isNullOrEmpty()) return ResponseEntity.badRequest().body("bad request")
        return ResponseEntity.noContent().build()
    }
}