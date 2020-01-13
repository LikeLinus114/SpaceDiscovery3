package alexcaywalt.magistracy.spacediscovery.di

import alexcaywalt.magistracy.spacediscovery.ApplicationContext
import alexcaywalt.magistracy.spacediscovery.di.module.ActivityModule
import alexcaywalt.magistracy.spacediscovery.di.module.ApiModule
import alexcaywalt.magistracy.spacediscovery.di.module.FragmentModule
import alexcaywalt.magistracy.spacediscovery.di.module.NetworkModule
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ApiModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {

    fun inject(spaceDiscoveryApp: ApplicationContext)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}