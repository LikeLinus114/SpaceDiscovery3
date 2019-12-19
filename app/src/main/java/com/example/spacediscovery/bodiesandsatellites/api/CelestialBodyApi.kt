package com.example.spacediscovery.bodiesandsatellites.api

import com.example.spacediscovery.bodiesandsatellites.CelestialBody
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface CelestialBodyApi {

    @GET("bodies_and_satellites")
    fun getBodiesAndSatellites(): Single<List<CelestialBody>>

}