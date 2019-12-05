package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainActivityLocalModel(
    val hasNotification: Boolean
) : Parcelable