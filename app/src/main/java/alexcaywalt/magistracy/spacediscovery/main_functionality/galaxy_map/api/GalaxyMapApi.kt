package alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.api

import alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.model.MapElement
import io.reactivex.Single
import retrofit2.http.GET

interface GalaxyMapApi {

    @GET("galaxy_map_elements")
    fun getGalaxyMapElements(): Single<List<MapElement>>

}