package com.sharifin.core

import android.content.Context
import android.os.Bundle

interface Router {
    fun taskDone(context: Context, bundle: Bundle?)
}