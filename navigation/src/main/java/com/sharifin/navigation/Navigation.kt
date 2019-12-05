package com.sharifin.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

interface Navigation {

    fun goto(
        context: Context,
        screen: Screen,
        needResult: Boolean = false,
        requestCode: Int = -1
    )

    fun goto(
            fragment: Fragment,
            screen: Screen,
            needResult: Boolean = false,
            requestCode: Int = -1
    )

    fun navBack()

    fun finishModule(context: Context)

    fun saveState(saveInstance: Bundle)

    fun restoreState(saveInstance: Bundle)
}