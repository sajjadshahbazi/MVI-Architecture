package com.sharifin.base

import android.content.Context
import android.os.Bundle
import com.sharifin.navigation.UnAuthorizedHandler

abstract class Navigator(var unAuthorizedHandler: UnAuthorizedHandler) {

    abstract fun finishModule(context: Context, bundle: Bundle)
}