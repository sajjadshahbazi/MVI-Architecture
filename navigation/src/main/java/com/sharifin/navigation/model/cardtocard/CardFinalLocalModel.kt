package com.sharifin.navigation.model.cardtocard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class CardFinalLocalModel(
    val sourceCardModel: CardLocalModel,
    val destinationCardModel: CardLocalModel,
    val price: String,
    val description: String,
    val transferInquiryModel: TransferInquiryLocalModel) : Parcelable