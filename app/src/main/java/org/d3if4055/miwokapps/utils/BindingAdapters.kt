package org.d3if4055.miwokapps.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

@BindingAdapter("showLoading")
fun toggleLoading(progressBar: ProgressBar, bool: Boolean) {
    progressBar.bringToFront()
    if (bool) {
        progressBar.visibility = View.VISIBLE
    }else {
        progressBar.visibility = View.INVISIBLE
    }
}