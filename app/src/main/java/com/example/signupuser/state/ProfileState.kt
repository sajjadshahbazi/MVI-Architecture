package com.example.signupuser.state

import com.sharifin.base.BaseState
import com.sharifin.base.mvibase.MviViewState

data class ProfileState(
    override val base: BaseState,
    val registerUser: Boolean,
    val maleClick : Boolean,
    val femaleClick : Boolean
) : MviViewState {

    companion object {
        @JvmStatic
        fun idle() =
            ProfileState(
                BaseState.stable(),
                registerUser = false,
                maleClick = false,
                femaleClick = false
            )
    }
}