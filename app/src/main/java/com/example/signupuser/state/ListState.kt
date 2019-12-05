package com.example.signupuser.state

import com.example.signupuser.model.UserUiModel
import com.sharifin.base.BaseState
import com.sharifin.base.mvibase.MviViewState

data class ListState(
    override val base: BaseState,
    val userUiModels: List<UserUiModel>
) : MviViewState {

    companion object {
        @JvmStatic
        fun idle() =
            ListState(
                BaseState.stable(),
                userUiModels = emptyList()
            )
    }
}