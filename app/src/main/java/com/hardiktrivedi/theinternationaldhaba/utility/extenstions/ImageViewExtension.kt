package com.hardiktrivedi.theinternationaldhaba.utility.extenstions

import android.widget.ImageView
import com.bumptech.glide.Glide


internal fun ImageView.loadUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .into(this)
}
