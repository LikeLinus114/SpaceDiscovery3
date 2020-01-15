package alexcaywalt.magistracy.spacediscovery.di.module

import alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.api.CelestialBodyApi
import alexcaywalt.magistracy.spacediscovery.galaxy_map.api.GalaxyMapApi
import alexcaywalt.magistracy.spacediscovery.location.api.SystemMapApi
import alexcaywalt.magistracy.spacediscovery.stations.api.StationsApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCelestialBodyApi(retrofit: Retrofit): CelestialBodyApi {
        return retrofit.create(CelestialBodyApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideStationsApi(retrofit: Retrofit): StationsApi {
        return retrofit.create(StationsApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideGalaxyMapApi(retrofit: Retrofit): GalaxyMapApi {
        return retrofit.create(GalaxyMapApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideSystemMapApi(retrofit: Retrofit): SystemMapApi {
        return retrofit.create(SystemMapApi::class.java)
    }

}