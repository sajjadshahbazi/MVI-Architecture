package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WebLocalModel(
        val url: String
) : Parcelable{
    companion object {
        const val EXTRA_WEB_LOCAL = "extra_web_local"
    }
}