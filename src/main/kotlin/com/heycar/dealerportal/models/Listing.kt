package com.heycar.dealerportal.models

data class Listing(
        val code: String,
        val make: String,
        val model: String,
        val kW: String,
        val year: String,
        val color: String,
        val price: String
)