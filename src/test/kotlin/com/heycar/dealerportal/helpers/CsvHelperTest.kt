package com.heycar.dealerportal.helpers

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CsvHelperTest{

    @Test
    internal fun `should read csv and return Listing`() {
        val csvStream = ("code,make/model,power-in-ps,year,color,price\n" +
                "1,mercedes/a 180,123,2014,black,15950\n"+
                "3,audi/r8,300,2020,blue,45000").byteInputStream()

        val listings = CsvHelper.csvToListings(csvStream)

        assertThat(listings[0].code).isEqualTo("1")
        assertThat(listings[0].make).isEqualTo("mercedes")
        assertThat(listings[0].model).isEqualTo("a 180")
        assertThat(listings[0].kW).isEqualTo("123")
        assertThat(listings[0].year).isEqualTo("2014")
        assertThat(listings[0].color).isEqualTo("black")
        assertThat(listings[0].price).isEqualTo("15950")

        assertThat(listings[1].code).isEqualTo("3")
        assertThat(listings[1].make).isEqualTo("audi")
        assertThat(listings[1].model).isEqualTo("r8")
        assertThat(listings[1].kW).isEqualTo("300")
        assertThat(listings[1].year).isEqualTo("2020")
        assertThat(listings[1].color).isEqualTo("blue")
        assertThat(listings[1].price).isEqualTo("45000")
    }
}