package com.example.spacediscovery.stations

import com.example.spacediscovery.R

class StationService {

    companion object {

        val preparedStations = prepareStationsData(createStations())

        private fun createStations(): List<Station> {
            return listOf(
                Station("Artur-45", StationType.ARTIFICIAL_SATELLITE, 100,null,
                    "artificial satellite of the Mars", R.drawable.space_satellite, null),
                Station("Vedder-84", StationType.ARTIFICIAL_SATELLITE, 200,"80%",
                    "artificial satellite of the Venus", R.drawable.space_satellite8, null),
                Station("Rock-20", StationType.SPACE_STATION, 100,null,
                    "industrial space station", R.drawable.space_station, null),
                Station("Rock-71", StationType.SPACE_STATION, 100,null,
                    "industrial space station", R.drawable.space_station2, null),
                Station("Rock-34", StationType.SPACE_STATION, 100,null,
                    "industrial space station", R.drawable.space_station3, null),
                Station("Rock-48", StationType.SPACE_STATION, 100,null,
                    "industrial space station", R.drawable.space_station4, null),
                Station("Red Ranger", StationType.SPACE_SHIP, 100,null,
                    "Spaceship-researcher of the solar system", R.drawable.spaceship1, null),
                Station("RAD", null, 400,null,
                    null, null, null)
            )
        }

        private fun prepareStationsData(stations: List<Station>): List<Station> {
            stations.forEach {
                if (it.type == null) {
                    it.type = StationType.UNKNOWN
                }
                if (it.signalQuality == null) {
                    it.signalQuality = "unknown"
                }
                if (it.description == null) {
                    it.description = "unknown"
                }
                if (it.imageResourceId == null) {
                    it.imageResourceId = R.drawable.no_image_available
                }
            }
            return stations
        }

    }

}