package com.example.spacediscovery.stations.api

import com.example.spacediscovery.stations.Station
import io.reactivex.Single
import retrofit2.http.GET

interface StationsApi {

    @GET("stations")
    fun getStations(): Single<List<Station>>

}