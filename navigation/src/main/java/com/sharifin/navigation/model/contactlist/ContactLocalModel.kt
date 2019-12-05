package com.sharifin.navigation.model.contactlist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactLocalModel(
    val id: Long? = null,
    val userId: Long? = null,
    val name: String? = null,
    val phone: String = "",
    val avatar: String = ""
) : Parcelable