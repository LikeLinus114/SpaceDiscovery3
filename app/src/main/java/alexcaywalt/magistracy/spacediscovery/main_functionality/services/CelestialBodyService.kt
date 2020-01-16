package alexcaywalt.magistracy.spacediscovery.main_functionality.services

import alexcaywalt.magistracy.spacediscovery.ApplicationContext
import alexcaywalt.magistracy.spacediscovery.R
import alexcaywalt.magistracy.spacediscovery.main_functionality.bodies_and_satellites.models.CelestialBody
import alexcaywalt.magistracy.spacediscovery.main_functionality.bodies_and_satellites.models.CelestialBodyTypeEnum
import android.graphics.BitmapFactory
import java.util.*

class CelestialBodyService {

    companion object {

        fun prepareBodiesData(bodies: List<CelestialBody>): List<CelestialBody> {
            bodies.forEach {
                if (it.type == null) {
                    it.type = CelestialBodyTypeEnum.UNKNOWN.id
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
            return bodies
        }

    }

}