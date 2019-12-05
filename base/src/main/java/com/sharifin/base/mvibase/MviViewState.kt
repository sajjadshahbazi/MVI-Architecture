package com.sharifin.base.mvibase

import com.sharifin.base.BaseState

/**
 * Immutable object which contains all the required information to render a [MviView].
 */
interface MviViewState{
    val base : BaseState
}
