package com.example.signupuser.state

import com.sharifin.base.BaseState
import com.sharifin.base.mvibase.MviViewState

data class MapState(
    override val base: BaseState,
    val goToFormView: Boolean,
    val lat : Double,
    val lng : Double
) : MviViewState {

    companion object {
        @JvmStatic
        fun idle() =
            MapState(
                BaseState.stable(),
                goToFormView = false,
                lat = 0.0,
                lng = 0.0
            )
    }
}