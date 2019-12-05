package com.sharifin.base.fragment_events

import android.os.Parcelable
import androidx.fragment.app.DialogFragment

class DialogEvent(
        val actionType: Int,
        val fragmentDialog: DialogFragment,
        val dialogFragment : Int,
        val objHolder : Parcelable?
)