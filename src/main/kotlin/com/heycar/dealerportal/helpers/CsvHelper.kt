package com.heycar.dealerportal.helpers

import com.heycar.dealerportal.models.Listing
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object CsvHelper {

    fun csvToListings(stream: InputStream): List<Listing> {
        try {
            val fileReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            val csvParser = CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())

            val csvRecords = csvParser.records
            return csvRecords.map {
                Listing(
                        code = it.get("code"),
                        make = it.get("make/model").split("/")[0],
                        model = it.get("make/model").split("/")[1],
                        kW = it.get("power-in-ps"),
                        year = it.get("year"),
                        color = it.get("color"),
                        price = it.get("price")
                )
            }
        } catch (e: IOException) {
            throw RuntimeException("fail to parse CSV file: " + e.localizedMessage);
        }
    }
}
