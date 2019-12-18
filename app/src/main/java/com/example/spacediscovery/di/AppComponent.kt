package com.example.spacediscovery.di

import android.app.Application
import com.example.spacediscovery.ApplicationContext
import com.example.spacediscovery.di.module.ActivityModule
import com.example.spacediscovery.di.module.ApiModule
import com.example.spacediscovery.di.module.FragmentModule
import com.example.spacediscovery.di.module.NetworkModule
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