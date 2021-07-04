package com.heycar.dealerportal.controllers

import com.heycar.dealerportal.helpers.CsvHelper
import com.heycar.dealerportal.services.ProviderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class CsvProviderController(private val providerService: ProviderService) {

    @PostMapping("/upload-csv/{dealerId}")
    fun uploadCsvListings(@PathVariable("dealerId") dealerId: String,
                          @RequestParam("file") listingsFile: MultipartFile): ResponseEntity<String> {
        if (dealerId.isEmpty()) return ResponseEntity.badRequest().body("bad request")

        providerService.save(dealerId, CsvHelper.csvToListings(listingsFile.inputStream))
        return ResponseEntity.ok().build()
    }
}