package alexcaywalt.magistracy.spacediscovery.location.viewmodel

import alexcaywalt.magistracy.spacediscovery.BasicViewModel
import alexcaywalt.magistracy.spacediscovery.ViewModelResult
import alexcaywalt.magistracy.spacediscovery.galaxy_map.model.MapElement
import alexcaywalt.magistracy.spacediscovery.location.api.SystemMapApi
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SystemMapViewModel @Inject constructor(var api: SystemMapApi): BasicViewModel() {

    val systemMapElements = MutableLiveData<ViewModelResult<List<MapElement>>>()

    fun fetchSystemMap() {
        systemMapElements.value =  ViewModelResult(arrayListOf(), loading = true, error = false)
        disposable.add(
            api.getSystemMapElements()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<MapElement>>() {
                    override fun onSuccess(receivedElements: List<MapElement>) {
                        Log.i("System Map ViewModel", "System Map elements have been received successfully")
                        systemMapElements.value =  ViewModelResult(receivedElements, loading = false, error = false)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("System Map ViewModel", "System Map elements receiving error", e)
                        systemMapElements.value =  ViewModelResult(arrayListOf(), loading = false, error = true)
                    }

                })
        )
    }

}