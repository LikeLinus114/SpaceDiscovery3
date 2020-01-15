package alexcaywalt.magistracy.spacediscovery

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BasicViewModel: ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}