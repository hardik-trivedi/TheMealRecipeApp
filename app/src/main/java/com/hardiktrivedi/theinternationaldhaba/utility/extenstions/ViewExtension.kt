package com.hardiktrivedi.theinternationaldhaba.utility.extenstions

import android.view.View
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable.INFINITE
import com.google.android.material.snackbar.Snackbar
import com.hardiktrivedi.theinternationaldhaba.R

internal fun View.visible() {
    this.visibility = View.VISIBLE
}

internal fun View.gone() {
    this.visibility = View.GONE
}

internal fun View.invisible() {
    this.visibility = View.INVISIBLE
}

internal fun View.showErrorInSnackbar(@StringRes message: Int, onActionClicked: () -> Unit) {
    val snackbar = Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_INDEFINITE
    ).setAction(R.string.retry) {
        onActionClicked.invoke()
    }
    snackbar.show()
}

internal fun LottieAnimationView.showErrorWithAnimation(
    @RawRes animation: Int, @StringRes message: Int, onActionClicked: () -> Unit
) {
    this.run {
        setLoopingAnimation(animation)
        showErrorInSnackbar(message) {
            onActionClicked.invoke()
        }
    }
}

internal fun LottieAnimationView.setLoopingAnimation(@RawRes animation: Int) {
    setAnimation(animation)
    repeatCount = INFINITE
    playAnimation()
}

