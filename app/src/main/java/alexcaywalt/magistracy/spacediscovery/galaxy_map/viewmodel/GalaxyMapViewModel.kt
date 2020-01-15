package alexcaywalt.magistracy.spacediscovery.galaxy_map.viewmodel

import alexcaywalt.magistracy.spacediscovery.BasicViewModel
import alexcaywalt.magistracy.spacediscovery.ViewModelResult
import alexcaywalt.magistracy.spacediscovery.galaxy_map.api.GalaxyMapApi
import alexcaywalt.magistracy.spacediscovery.galaxy_map.model.MapElement
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GalaxyMapViewModel @Inject constructor(var api: GalaxyMapApi): BasicViewModel() {

    val galaxyMapElements = MutableLiveData<ViewModelResult<List<MapElement>>>()

    fun fetchGalaxyMap() {
        galaxyMapElements.value =  ViewModelResult(arrayListOf(), loading = true, error = false)
        disposable.add(
            api.getGalaxyMapElements()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<MapElement>>() {
                    override fun onSuccess(receivedElements: List<MapElement>) {
                        Log.i("Galaxy Map ViewModel", "Galaxy Map elements have been received successfully")
                        galaxyMapElements.value =  ViewModelResult(receivedElements, loading = false, error = false)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Galaxy Map ViewModel", "Galaxy Map elements receiving error", e)
                        galaxyMapElements.value =  ViewModelResult(arrayListOf(), loading = false, error = true)
                    }

                })
        )
    }

}