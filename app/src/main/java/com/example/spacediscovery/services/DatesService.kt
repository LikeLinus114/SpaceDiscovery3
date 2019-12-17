package com.example.spacediscovery.services

import java.time.LocalDate

class DatesService {

    companion object {

        val dates =
            createDates()

        private fun createDates(): List<LocalDate> {
            return listOf(
                LocalDate.of(2019, 7, 11),
                LocalDate.of(2019, 7, 12),
                LocalDate.of(2019, 7, 14)
            )
        }

    }

}