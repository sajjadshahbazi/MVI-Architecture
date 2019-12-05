package com.example.signupuser.action

import com.sharifin.base.BaseAction

sealed class ListAction : BaseAction() {
    object InitialAction : ListAction()
}