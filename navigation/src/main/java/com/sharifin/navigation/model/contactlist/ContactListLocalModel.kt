package com.sharifin.navigation.model.contactlist

import android.os.Parcelable
import androidx.appcompat.widget.DialogTitle
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactListLocalModel(
        val title: String?,
        val description : String?,
        val isMobankMember : Boolean = false
):Parcelable



