package com.sharifin.navigation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilteredActivityHistoryLocalModel (
        val types : String,
        val fromDate : Long,
        val toDate : Long
): Parcelable