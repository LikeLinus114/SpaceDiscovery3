package com.example.spacediscovery.di.module

import com.example.spacediscovery.bodiesandsatellites.BodiesSatellitesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBodiesSatellitesFragment(): BodiesSatellitesFragment

}