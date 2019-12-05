package com.example.signupuser.result


import com.sharifin.base.BaseResult
import com.sharifin.retrofit.ErrorHolder

sealed class MapResult : BaseResult() {
    object LastStable : MapResult()
    data class Confirm(
        val lat : Double = 0.0,
        val lng : Double = 0.0
    ) : MapResult()
}