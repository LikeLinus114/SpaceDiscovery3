package com.example.spacediscovery.services

import android.graphics.BitmapFactory
import com.example.spacediscovery.ApplicationContext
import com.example.spacediscovery.R
import com.example.spacediscovery.stations.Station
import com.example.spacediscovery.stations.StationTypeEnum
import java.util.*

class StationService {

    companion object {

        fun prepareStationsData(stations: List<Station>): List<Station> {
            stations.forEach {
                if (it.type == null) {
                    it.type = StationTypeEnum.UNKNOWN.id
                }
                if (it.signalQuality == null) {
                    it.signalQuality = "unknown"
                }
                if (it.description == null) {
                    it.description = "unknown"
                }
                if (it.encodedImage == null) {
                    it.imageBitMap = BitmapFactory.decodeResource(ApplicationContext.getContext().resources, R.drawable.no_image_available)
                } else {
                    val byteArray = Base64.getMimeDecoder().decode(it.encodedImage)
                    it.imageBitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
                }
            }
            return stations
        }

    }

}