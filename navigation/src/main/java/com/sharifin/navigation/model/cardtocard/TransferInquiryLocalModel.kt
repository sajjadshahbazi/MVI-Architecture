package com.sharifin.navigation.model.cardtocard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TransferInquiryLocalModel(
    val bankName: String,
    val cardOwnerName: String,
    val transferResNum: String
): Parcelable