package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalletLocalModel(
    val id: String,
    val balance: String = "",
    val name: String = "",
    val ownerName: String = "",
    val isDefault: Boolean = false
) : Parcelable