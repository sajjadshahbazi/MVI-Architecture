package com.example.signupuser.intent

import com.sharifin.base.mvibase.MviIntent

sealed class ListIntent : MviIntent {
    object InitialIntent : ListIntent()
}