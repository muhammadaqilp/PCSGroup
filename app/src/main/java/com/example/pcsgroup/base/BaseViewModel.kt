package com.example.pcsgroup.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    protected val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    fun Disposable.disposedBy(compositeDisposable: CompositeDisposable?) {
        compositeDisposable?.add(this)
    }

}