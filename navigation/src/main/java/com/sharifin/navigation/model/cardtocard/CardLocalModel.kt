package com.sharifin.navigation.model.cardtocard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CardLocalModel(
    val id: Long? = null,
    val pan: String? = null,
    val expiryDate: String = "",
    val name: String = "",
    val bank: String = "",
    val logo: String = ""
) : Parcelable {

    companion object {
        @JvmStatic
        fun createWithPan(pan: String) = CardLocalModel(pan = pan)
    }
}