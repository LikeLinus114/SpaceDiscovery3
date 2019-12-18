package com.example.spacediscovery.di.module

import com.example.spacediscovery.bodiesandsatellites.api.CelestialBodyApi
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

}