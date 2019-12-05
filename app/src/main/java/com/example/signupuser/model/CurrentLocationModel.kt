package com.example.signupuser.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CurrentLocationLocalModel(
    val lat : Double = 0.0,
    val lng : Double = 0.0
):Parcelable