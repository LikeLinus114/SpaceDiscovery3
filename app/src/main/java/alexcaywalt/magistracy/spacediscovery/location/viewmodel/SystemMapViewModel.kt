package alexcaywalt.magistracy.spacediscovery.location.viewmodel

import alexcaywalt.magistracy.spacediscovery.galaxymap.model.MapElement
import alexcaywalt.magistracy.spacediscovery.location.api.SystemMapApi
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SystemMapViewModel @Inject constructor(var api: SystemMapApi): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val systemMapElements = MutableLiveData<List<MapElement>>()
    val error = MutableLiveData<Boolean>()

    fun fetchSystemMap() {
        loading.value = true
        disposable.add(
            api.getSystemMapElements()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<MapElement>>() {
                    override fun onSuccess(receivedElements: List<MapElement>) {
                        Log.i("System Map ViewModel", "System Map elements have been received successfully")
                        systemMapElements.value = receivedElements
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("System Map ViewModel", "System Map elements receiving error", e)
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