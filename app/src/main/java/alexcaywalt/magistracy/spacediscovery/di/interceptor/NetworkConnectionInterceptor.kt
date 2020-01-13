package alexcaywalt.example.spacediscovery.di.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConnectionInterceptor @Inject constructor(): Interceptor {

    companion object {

        private fun isInternetAvailable() = true

        private fun showNoNetworkConnection() = Unit

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (isInternetAvailable()) {
            showNoNetworkConnection()
        }
        return chain.proceed(request)
    }

}