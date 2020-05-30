package com.hardiktrivedi.theinternationaldhaba.viewstate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.hardiktrivedi.theinternationaldhaba.exception.NoInternetAvailableException
import com.hardiktrivedi.theinternationaldhaba.exception.SomethingWentWrongException

/**
 * An abstract class representing abstraction of various observers.
 * This class will serve as a base class where child class needs to provide implementation for
 * all abstract property of type [Observer]
 */
abstract class ViewStateAwareFragment : Fragment() {

    /**
     * [Observer] of type [Boolean]. View will provide it's implementation.
     * If true progress needs to be displayed else hide the view.
     *
     * @see [ViewStateAwareFragment]
     */
    abstract val progressObserver: Observer<Boolean>

    /**
     * [Observer] of type [NoInternetAvailableException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    abstract val networkErrorObserver: Observer<NoInternetAvailableException>

    /**
     * [Observer] of type [SomethingWentWrongException]. View will provide it's implementation.
     *
     * @see [ViewStateAwareFragment]
     */
    abstract val genericErrorObserver: Observer<SomethingWentWrongException>

    /**
     * An abstract property of type [ViewStateAwareViewModel]. Child class will bind it's view model
     * with this property. After binding the ViewModel class will get all required LiveData for free.
     */
    abstract val viewModel: ViewStateAwareViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewStateObservers()
    }

    /**
     * Sets the observers to respective live data instances.
     */
    private fun setViewStateObservers() {
        viewModel.run {
            progress.observe(viewLifecycleOwner, progressObserver)
            networkException.observe(viewLifecycleOwner, networkErrorObserver)
            somethingWentWrongException.observe(viewLifecycleOwner, genericErrorObserver)
        }
    }
}
