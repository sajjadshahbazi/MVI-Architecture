package com.sharifin.navigation.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalletDetailLocalModel(
    val id: String,
    val name: String,
    val balance: String = "",
    val ownerName: String = "",
    val isDefault: Boolean = false
) : Parcelable