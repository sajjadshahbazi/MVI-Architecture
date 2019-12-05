package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


sealed class NotificationActivityLocalModel : Parcelable {

    @Parcelize
    object Message : NotificationActivityLocalModel()

    @Parcelize
    data class Request(
            val requestID: String,
            val shouldPay: Boolean
    ) : NotificationActivityLocalModel()

}
