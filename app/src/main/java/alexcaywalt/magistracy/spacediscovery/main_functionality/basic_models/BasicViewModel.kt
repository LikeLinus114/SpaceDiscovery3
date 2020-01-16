package alexcaywalt.magistracy.spacediscovery.main_functionality.basic_models

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BasicViewModel: ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}