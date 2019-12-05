package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginLocalModel(
    val phone: String = "",
    val fromNotification: Boolean = false,
    val hasMessage: Boolean = false,
    val message: String = ""
) : Parcelable