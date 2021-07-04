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

    fun createListings(): List<Listing> {
        return listOf(
                Listing(code = "c",
                        make = "Volkwagen",
                        model = "m10",
                        kW = "123",
                        year = "2020",
                        color = "black",
                        price = "29030"),
                Listing(code = "b",
                        make = "Audi",
                        model = "r8",
                        kW = "123",
                        year = "2017",
                        color = "white",
                        price = "29030"),
                Listing(code = "d",
                        make = "Audi",
                        model = "r9",
                        kW = "123",
                        year = "2017",
                        color = "black",
                        price = "29030"))
    }

}