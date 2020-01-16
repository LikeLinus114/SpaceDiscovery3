package alexcaywalt.magistracy.spacediscovery.di.module

import alexcaywalt.magistracy.spacediscovery.main_functionality.bodies_and_satellites.BodiesSatellitesFragment
import alexcaywalt.magistracy.spacediscovery.main_functionality.galaxy_map.GalaxyMapFragment
import alexcaywalt.magistracy.spacediscovery.main_functionality.location.LocationFragment
import alexcaywalt.magistracy.spacediscovery.main_functionality.stations.StationsFragment
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