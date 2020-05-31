package com.hardiktrivedi.theinternationaldhaba.viewstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * A view model which is an abstract class. It holds the bunch of LiveData properties
 */
abstract class ViewStateAwareViewModel : ViewModel() {
    val compositeDisposable = CompositeDisposable()

    fun dispose() {
        compositeDisposable.clear()
    }

    /**
     * Mutable instance which can post the updated value of type NoInternetAvailableException
     */
    private val _networkException = MutableLiveData<NoInternetAvailableException>()

    /**
     * Mutable instance which can post the updated value of type SomethingWentWrongException
     */
    private val _somethingWentWrongException = MutableLiveData<SomethingWentWrongException>()

    /**
     * Mutable instance which can post the updated value of type Boolean
     */
    protected val _progress = MutableLiveData<Boolean>()

    /**
     * Immutable instance which can post the updated value of type NoInternetAvailableException
     */
    val networkException: LiveData<NoInternetAvailableException> = _networkException

    /**
     * Immutable instance which can post the updated value of type SomethingWentWrongException
     */
    val somethingWentWrongException: LiveData<SomethingWentWrongException> =
        _somethingWentWrongException

    /**
     * Immutable instance which can post the updated value of type Boolean
     */
    val progress: LiveData<Boolean> = _progress

    /**
     * Receives instance of type [Throwable] and expect it to be either [NoInternetAvailableException] or
     * [SomethingWentWrongException]
     */
    fun handleError(e: Throwable) {
        when (e) {
            is NoInternetAvailableException -> _networkException.postValue(e)
            is SomethingWentWrongException -> _somethingWentWrongException.postValue(e)
        }
    }
}
