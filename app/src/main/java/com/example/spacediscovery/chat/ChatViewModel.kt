package com.example.spacediscovery.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacediscovery.stations.Station
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChatViewModel @Inject constructor(): ViewModel() {

    val disposable = CompositeDisposable()
    val loading = MutableLiveData<Boolean>()

    val chats = MutableLiveData<List<Chat>>()
    val error = MutableLiveData<Boolean>()

    fun fetchChats() {
        loading.value = true
        disposable.add(
            Single.just(
                arrayListOf<Chat>(
                    /*Chat()*/
                )
            )
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Chat>>() {
                    override fun onSuccess(receivedChats: List<Chat>) {
                        Log.i("Chat ViewModel", "Chats have been received successfully")
                        chats.value = receivedChats
                        error.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Chat ViewModel", "Chats receiving error", e)
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