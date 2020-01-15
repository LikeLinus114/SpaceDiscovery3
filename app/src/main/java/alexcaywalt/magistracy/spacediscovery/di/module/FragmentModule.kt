package alexcaywalt.magistracy.spacediscovery.di.module

import alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.BodiesSatellitesFragment
import alexcaywalt.magistracy.spacediscovery.galaxy_map.GalaxyMapFragment
import alexcaywalt.magistracy.spacediscovery.location.LocationFragment
import alexcaywalt.magistracy.spacediscovery.stations.StationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeBodiesSatellitesFragment(): BodiesSatellitesFragment

    @ContributesAndroidInjector
    abstract fun contributeStationsFragment(): StationsFragment

    @ContributesAndroidInjector
    abstract fun contributeGalaxyMapFragment(): GalaxyMapFragment

    @ContributesAndroidInjector
    abstract fun contributeLocationFragment(): LocationFragment

}