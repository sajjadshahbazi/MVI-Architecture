package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OtpLocalModel(
    val phone: String,
    val type: String,
    val password: String?,
    val email: String?,
    val fingerPrint: String = "",
    val androidVersion: String = "",
    val deviceID: String = "",
    val otpToken: String = "",
    val fromNotification: Boolean = false

) : Parcelable