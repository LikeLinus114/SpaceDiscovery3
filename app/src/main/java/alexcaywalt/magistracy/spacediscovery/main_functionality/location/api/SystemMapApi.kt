package alexcaywalt.magistracy.spacediscovery.main_functionality.location.api

import alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.model.MapElement
import io.reactivex.Single
import retrofit2.http.GET

interface SystemMapApi {

    @GET("system_map_elements")
    fun getSystemMapElements(): Single<List<MapElement>>

}