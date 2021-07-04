package com.heycar.dealerportal.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class DealerController {

    @PostMapping("/upload-csv/{dealerId}")
    fun uploadListing(@PathVariable("dealerId") dealerId: String,
                      @RequestParam("file") listingFile: MultipartFile): ResponseEntity<String> {
        if(dealerId.isNullOrEmpty()) return ResponseEntity.badRequest().body("bad request")
        return ResponseEntity.noContent().build()
    }

}