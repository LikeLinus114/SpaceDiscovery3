package alexcaywalt.magistracy.spacediscovery.main_functionality.services

import alexcaywalt.magistracy.spacediscovery.ApplicationContext
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.models.Station
import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.models.StationTypeEnum
import android.graphics.BitmapFactory
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
                    it.description = "Description: unknown"
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