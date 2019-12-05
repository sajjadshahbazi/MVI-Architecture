package com.example.signupuser.result


import com.sharifin.base.BaseResult
import com.sharifin.retrofit.ErrorHolder

sealed class ProfileResult : BaseResult() {
    object LastStable : ProfileResult()
    object MaleClick : ProfileResult()
    object FemaleClick : ProfileResult()
    object Loading : ProfileResult()
    object RegisterUser : ProfileResult()
    class Error(
        val err: ErrorHolder
    ) : ProfileResult()
}