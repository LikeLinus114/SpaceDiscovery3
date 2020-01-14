package alexcaywalt.magistracy.spacediscovery.location.api

import alexcaywalt.magistracy.spacediscovery.galaxymap.model.MapElement
import io.reactivex.Single
import retrofit2.http.GET

interface SystemMapApi {

    @GET("system_map_elements")
    fun getSystemMapElements(): Single<List<MapElement>>

}