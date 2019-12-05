package com.sharifin.navigation.model.wallet

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WalletTransferLocalModelInWallet(
        val walletId: String,
        val name: String = "",
        val balance: Long = 0,
        val formattedBalance: String
) : Parcelable