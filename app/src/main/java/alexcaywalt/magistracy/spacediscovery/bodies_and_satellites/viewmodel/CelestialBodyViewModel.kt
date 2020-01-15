package alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.viewmodel

import alexcaywalt.magistracy.spacediscovery.BasicViewModel
import alexcaywalt.magistracy.spacediscovery.ViewModelResult
import alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.api.CelestialBodyApi
import alexcaywalt.magistracy.spacediscovery.bodies_and_satellites.models.CelestialBody
import alexcaywalt.magistracy.spacediscovery.services.CelestialBodyService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CelestialBodyViewModel @Inject constructor(var api: CelestialBodyApi): BasicViewModel() {

    val celestialBodies = MutableLiveData<ViewModelResult<List<CelestialBody>>>()

    fun fetchBodiesAndSatellites() {
        celestialBodies.value = ViewModelResult(arrayListOf(), loading = true, error = false)
        disposable.add(
            api.getBodiesAndSatellites()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<CelestialBody>>() {
                    override fun onSuccess(receivedBodies: List<CelestialBody>) {
                        Log.i("Celestial Body ViewModel", "Celestial bodies have been received successfully")
                        celestialBodies.value = ViewModelResult(
                            CelestialBodyService.prepareBodiesData(receivedBodies),
                            loading = false,
                            error = false
                        )
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Celestial Body ViewModel", "Celestial bodies receiving error", e)
                        celestialBodies.value = ViewModelResult(arrayListOf(), loading = false, error = true)
                    }

                })
        )
    }

}