package alexcaywalt.magistracy.spacediscovery.galaxymap.api

import alexcaywalt.magistracy.spacediscovery.galaxymap.model.MapElement
import io.reactivex.Single
import retrofit2.http.GET

interface GalaxyMapApi {

    @GET("galaxy_map_elements")
    fun getGalaxyMapElements(): Single<List<MapElement>>

}