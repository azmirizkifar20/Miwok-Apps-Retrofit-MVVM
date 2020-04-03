package org.d3if4055.miwokapps.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Suppress("SpellCheckingInspection")
@Parcelize
data class WordList (
    val defaultWord: String?,
    val miwokWord: String?,
    val image: String?
) : Parcelable