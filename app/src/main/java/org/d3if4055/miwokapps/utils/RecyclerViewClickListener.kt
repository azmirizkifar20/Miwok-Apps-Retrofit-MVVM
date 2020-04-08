package org.d3if4055.miwokapps.utils

import android.view.View
import org.d3if4055.miwokapps.model.Miwok

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClicked(view: View, miwok: Miwok) {}

}