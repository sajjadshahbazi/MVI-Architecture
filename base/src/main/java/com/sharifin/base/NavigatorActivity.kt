package com.sharifin.base

import android.os.Bundle
import com.sharifin.navigation.Navigation
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class NavigatorActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var newNavigator: Navigation

    override fun finish() {
        newNavigator.navBack()
        super.finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val state = outState ?: Bundle()
        newNavigator.saveState(state)
        super.onSaveInstanceState(state)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        val state = savedInstanceState ?: Bundle()
        newNavigator.restoreState(state)
        super.onRestoreInstanceState(savedInstanceState)
    }
}