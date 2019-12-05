package com.sharifin.base

import com.sharifin.base.mvibase.MviAction

open class BaseAction : MviAction {
    data class Initial(val initData : Any) : BaseAction()
}