package com.example.spacediscovery.bodiesandsatellites.viewmodel

import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.spacediscovery.bodiesandsatellites.BodiesSatellitesFragment
import com.example.spacediscovery.bodiesandsatellites.CelestialBody
import com.example.spacediscovery.bodiesandsatellites.api.CelestialBodyApi
import com.example.spacediscovery.services.CelestialBodyService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.*
import javax.inject.Inject

class CelestialBodyViewModel @Inject constructor(var api: CelestialBodyApi): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val celestialBodies = MutableLiveData<List<CelestialBody>>()
    val error = MutableLiveData<Boolean>()

    private lateinit var lifecycleOwner: LifecycleOwner

    fun fetchBodiesAndSatellites(fragment: BodiesSatellitesFragment) {
        lifecycleOwner = fragment
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