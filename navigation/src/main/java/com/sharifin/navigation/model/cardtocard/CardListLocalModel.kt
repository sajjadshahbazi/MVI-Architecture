package com.sharifin.navigation.model.cardtocard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CardListLocalModel(
    val type: String
) : Parcelable