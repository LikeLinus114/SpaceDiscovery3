package com.example.spacediscovery.services

import android.graphics.BitmapFactory
import com.example.spacediscovery.ApplicationContext
import com.example.spacediscovery.R
import com.example.spacediscovery.bodiesandsatellites.CelestialBody
import com.example.spacediscovery.bodiesandsatellites.CelestialBodyTypeEnum

class CelestialBodyService {

    companion object {

        fun prepareBodiesData(bodies: List<CelestialBody>): List<CelestialBody> {
            bodies.forEach {
                if (it.type == null) {
                    it.type =
                        CelestialBodyTypeEnum.UNKNOWN.id
                }
                if (it.description == null) {
                    it.description = "unknown"
                }
                if (it.imageUrl == null) {
                    it.imageBitMap = BitmapFactory.decodeResource(ApplicationContext.getContext().resources, R.drawable.no_image_available)
                }
            }
            return bodies
        }

    }

}