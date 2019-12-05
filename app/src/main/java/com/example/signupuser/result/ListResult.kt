package com.example.signupuser.result


import com.example.signupuser.model.UserUiModel
import com.sharifin.base.BaseResult
import com.sharifin.retrofit.ErrorHolder

sealed class ListResult : BaseResult() {
    object LastStable : ListResult()
    class UserList(
        val users : List<UserUiModel>
    ) : ListResult()
    data class Error(val err: ErrorHolder) : ListResult()
    object Loading : ListResult()
}