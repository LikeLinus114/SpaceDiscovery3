package alexcaywalt.magistracy.spacediscovery.galaxymap.viewmodel

import alexcaywalt.magistracy.spacediscovery.galaxymap.api.GalaxyMapApi
import alexcaywalt.magistracy.spacediscovery.galaxymap.model.MapElement
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GalaxyMapViewModel @Inject constructor(var api: GalaxyMapApi): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val galaxyMapElements = MutableLiveData<List<MapElement>>()
    val error = MutableLiveData<Boolean>()

    fun fetchGalaxyMap() {
        loading.value = true
        disposable.add(
            api.getGalaxyMapElements()
            //Single.just(arrayListOf<MapElement>())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<MapElement>>() {
                    override fun onSuccess(receivedElements: List<MapElement>) {
                        Log.i("Galaxy Map ViewModel", "Galaxy Map elements have been received successfully")
                        galaxyMapElements.value = receivedElements
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Galaxy Map ViewModel", "Galaxy Map elements receiving error", e)
                        error.value = true
                        loading.value = false
                    }

                })
        )
    }

}