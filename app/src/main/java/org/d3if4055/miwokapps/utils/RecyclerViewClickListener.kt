package org.d3if4055.miwokapps.utils

import android.view.View
import org.d3if4055.miwokapps.data.Miwok

interface RecyclerViewClickListener {

    fun onRecyclerViewItemMiwokClicked(view: View, miwok: Miwok) {}
    fun onRecyclerViewItemWordListClicked(view: View, miwok: Miwok) {}

}