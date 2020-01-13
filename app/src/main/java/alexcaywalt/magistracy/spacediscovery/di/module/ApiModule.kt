package alexcaywalt.magistracy.spacediscovery.di.module

import alexcaywalt.magistracy.spacediscovery.bodiesandsatellites.api.CelestialBodyApi
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

}