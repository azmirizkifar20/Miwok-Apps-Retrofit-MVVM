package org.d3if4055.miwokapps.ui.recyclerview

import android.view.View
import org.d3if4055.miwokapps.data.Miwok
import org.d3if4055.miwokapps.data.WordList

interface RecyclerViewClickListener {

    fun onRecyclerViewItemMiwokClicked(view: View, miwok: Miwok) {}
    fun onRecyclerViewItemWordListClicked(view: View, wordList: WordList) {}

}