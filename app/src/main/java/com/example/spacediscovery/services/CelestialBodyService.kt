package com.example.spacediscovery.services

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
                if (it.imageResourceId == null) {
                    it.imageResourceId = R.drawable.no_image_available
                }
            }
            return bodies
        }

    }

}