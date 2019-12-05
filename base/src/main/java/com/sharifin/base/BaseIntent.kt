package com.sharifin.base

import com.sharifin.base.mvibase.MviIntent

open class BaseIntent : MviIntent {
    data class Initial(val initData : Any) : BaseIntent()
}