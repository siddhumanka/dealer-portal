package com.heycar.dealerportal.testhelpers

import com.heycar.dealerportal.models.Listing

object TestHelper {

    fun createListing(): Listing {
        return Listing(code = "a",
                make = "Audi",
                model = "r8",
                kW = "123",
                year = "2017",
                color = "black",
                price = "29030")
    }

}