package com.sharifin.navigation.model.payment

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class InvoiceLocalModel(
        val invoiceType: String,
        val invoiceSign: String?,
        val walletID: Long?,
        val title: String?,
        val description: String?,
        val price: Long = 0,
        val icon: String?,
        val toolbarTitle: String = "پرداخت"
) : Parcelable {
    companion object {
        const val INVOICE_TYPE_LAZY = "lazy"
        const val INVOICE_TYPE_PREFETCH = "prefetch"
        const val INVOICE_TYPE_CREATE = "create"
    }
}