package alexcaywalt.magistracy.spacediscovery.stations.viewmodel

import alexcaywalt.magistracy.spacediscovery.BasicViewModel
import alexcaywalt.magistracy.spacediscovery.ViewModelResult
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

class StationViewModel @Inject constructor(var api: StationsApi): BasicViewModel() {

    val stations = MutableLiveData<ViewModelResult<List<Station>>>()

    fun fetchStations() {
        stations.value = ViewModelResult(arrayListOf(), loading = true, error = false)
        disposable.add(
            api.getStations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Station>>() {
                    override fun onSuccess(receivedStations: List<Station>) {
                        Log.i("Station ViewModel", "Stations have been received successfully")
                        stations.value = ViewModelResult(
                            StationService.prepareStationsData(receivedStations),
                            loading = false,
                            error = false
                        )
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Station ViewModel", "Stations receiving error", e)
                        stations.value = ViewModelResult(arrayListOf(), loading = false, error = true)
                    }

                })
        )
    }

}