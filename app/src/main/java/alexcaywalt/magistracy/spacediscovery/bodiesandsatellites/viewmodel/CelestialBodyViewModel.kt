package alexcaywalt.magistracy.spacediscovery.bodiesandsatellites.viewmodel

import alexcaywalt.magistracy.spacediscovery.bodiesandsatellites.api.CelestialBodyApi
import alexcaywalt.magistracy.spacediscovery.bodiesandsatellites.models.CelestialBody
import alexcaywalt.magistracy.spacediscovery.services.CelestialBodyService
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CelestialBodyViewModel @Inject constructor(var api: CelestialBodyApi): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val celestialBodies = MutableLiveData<List<CelestialBody>>()
    val error = MutableLiveData<Boolean>()

    fun fetchBodiesAndSatellites() {
        loading.value = true
        disposable.add(
            api.getBodiesAndSatellites()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<CelestialBody>>() {
                    override fun onSuccess(receivedBodies: List<CelestialBody>) {
                        Log.i("Celestial Body ViewModel", "Celestial bodies have been received successfully")
                        celestialBodies.value = CelestialBodyService.prepareBodiesData(receivedBodies)
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Celestial Body ViewModel", "Celestial bodies receiving error", e)
                        error.value = true
                        loading.value = false
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}