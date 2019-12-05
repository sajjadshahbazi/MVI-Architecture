package com.example.signupuser.intent

import com.sharifin.base.mvibase.MviIntent

sealed class MapIntent : MviIntent {
    object InitialIntent : MapIntent()
    data class ConfirmIntent(
        val lat : Double = 0.0,
        val lng : Double = 0.0
    ) : MapIntent()
}