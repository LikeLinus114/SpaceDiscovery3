package com.example.spacediscovery.services

import com.example.spacediscovery.R
import com.example.spacediscovery.bodiesandsatellites.CelestialBody
import com.example.spacediscovery.bodiesandsatellites.CelestialBodyType

class CelestialBodyService {

    companion object {

        val preparedBodies =
            prepareBodiesData(
                createBodies()
            )

        private fun createBodies(): List<CelestialBody> {
            return listOf(
                CelestialBody(
                    "Mercury",
                    CelestialBodyType.PLANET,
                    100,
                    "Mercury is the smallest and innermost planet in the Solar System. Its orbital period around the Sun of 87.97 days is the shortest of all the planets in the Solar System. It is named after the Roman deity Mercury, the messenger of the gods.",
                    R.drawable.mercury
                ),
                CelestialBody(
                    "Venus",
                    CelestialBodyType.PLANET,
                    100,
                    "Venus is the second planet from the Sun, orbiting it every 224.7 Earth days. With a rotation period of 243 Earth days, Venus takes longer to rotate about its axis than any planet in the Solar System.",
                    R.drawable.venus
                ),
                CelestialBody(
                    "Earth",
                    CelestialBodyType.PLANET,
                    100,
                    "Earth is the third planet from the Sun and the only known astronomical object in the Universe known to harbor life. From Earth, the two brightest celestial bodies are the Sun and the Moon, which during a few million years have almost the same size in the sky, a diameter of about 0.5°, causing the Moon to regularly occult the Sun.",
                    R.drawable.earth
                ),
                CelestialBody(
                    "Mars",
                    CelestialBodyType.PLANET,
                    100,
                    "Mars is the fourth planet from the Sun and the second-smallest planet in the Solar System after Mercury. In English, Mars carries a name of the Roman god of war, and is often referred to as the Red Planet",
                    R.drawable.mars
                ),
                CelestialBody(
                    "The Moon",
                    CelestialBodyType.SATELLITE,
                    100,
                    "Earth's Moon is an astronomical body that orbits the planet and acts as its only permanent natural satellite. It is the fifth-largest satellite in the Solar System, and the largest among planetary satellites relative to the size of the planet that it orbits (its primary).",
                    R.drawable.moon
                ),
                CelestialBody(
                    "Asteroid 432",
                    CelestialBodyType.ASTEROID,
                    100,
                    null,
                    R.drawable.asteroid_432
                ),
                CelestialBody(
                    "Asteroid 547",
                    null,
                    100,
                    null,
                    null
                )
            )
        }

        private fun prepareBodiesData(bodies: List<CelestialBody>): List<CelestialBody> {
            bodies.forEach {
                if (it.type == null) {
                    it.type =
                        CelestialBodyType.UNKNOWN
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