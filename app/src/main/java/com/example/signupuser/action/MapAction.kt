package com.example.signupuser.action

import com.example.signupuser.intent.MapIntent
import com.sharifin.base.BaseAction

sealed class MapAction : BaseAction() {
    object InitialAction : MapAction()
    data class ConfirmIntent(
        val lat : Double = 0.0,
        val lng : Double = 0.0
    ) : MapAction()
}