package com.example.spacediscovery.bodiesandsatellites.api

import com.example.spacediscovery.bodiesandsatellites.models.CelestialBody
import io.reactivex.Single
import retrofit2.http.GET

interface CelestialBodyApi {

    @GET("bodies_and_satellites")
    fun getBodiesAndSatellites(): Single<List<CelestialBody>>

}