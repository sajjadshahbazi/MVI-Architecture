package com.sharifin.navigation

import android.content.Context
import android.os.Parcelable

interface UnAuthorizedHandler {
    fun userUnAuthorized(context: Context, nav: Navigation, message: String)
}