package alexcaywalt.magistracy.spacediscovery.main_functionality.stations.api

import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.models.Station
import io.reactivex.Single
import retrofit2.http.GET

interface StationsApi {

    @GET("stations")
    fun getStations(): Single<List<Station>>

}