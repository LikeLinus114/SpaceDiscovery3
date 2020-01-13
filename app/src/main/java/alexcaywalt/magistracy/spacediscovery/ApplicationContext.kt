package alexcaywalt.magistracy.spacediscovery

import alexcaywalt.magistracy.spacediscovery.di.AppInjector
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * This singleton is used to get the application context from anywhere
 */
class ApplicationContext: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = androidInjector

    companion object {

        private lateinit var appContext: ApplicationContext

        fun getContext(): ApplicationContext {
            return appContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        AppInjector.init(this)
    }

}