package com.swvl.movie.base.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private lateinit var d: CompositeDisposable

    fun onCreate() {
        if (this::d.isInitialized) {
            return
        }
        d = CompositeDisposable()
        onBind()
    }

    abstract fun onBind()

    fun Disposable.addToCompositeDisposable() {
        d.add(this)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    public override fun onCleared() {
        d.dispose()
        super.onCleared()
    }
}
