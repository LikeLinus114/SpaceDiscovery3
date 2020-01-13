package alexcaywalt.magistracy.spacediscovery.stations.viewmodel

import alexcaywalt.magistracy.spacediscovery.services.StationService
import alexcaywalt.magistracy.spacediscovery.stations.api.StationsApi
import alexcaywalt.magistracy.spacediscovery.stations.models.Station
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StationViewModel @Inject constructor(var api: StationsApi): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val stations = MutableLiveData<List<Station>>()
    val error = MutableLiveData<Boolean>()

    fun fetchStations() {
        loading.value = true
        disposable.add(
            api.getStations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Station>>() {
                    override fun onSuccess(receivedStations: List<Station>) {
                        Log.i("Station ViewModel", "Stations have been received successfully")
                        stations.value = StationService.prepareStationsData(receivedStations)
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Station ViewModel", "Stations receiving error", e)
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