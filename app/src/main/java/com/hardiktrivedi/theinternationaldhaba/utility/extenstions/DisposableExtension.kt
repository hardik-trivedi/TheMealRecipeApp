package com.hardiktrivedi.theinternationaldhaba.utility.extenstions

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

fun Disposable.asDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}