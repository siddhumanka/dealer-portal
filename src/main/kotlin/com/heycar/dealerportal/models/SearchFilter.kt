package com.heycar.dealerportal.models

data class SearchFilter(
        val make: String? = null,
        val model: String? = null,
        val year: String? = null,
        val color: String? = null
)