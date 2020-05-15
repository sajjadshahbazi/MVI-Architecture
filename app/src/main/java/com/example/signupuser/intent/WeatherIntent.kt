package com.example.signupuser.intent

import com.sharifin.base.mvibase.MviIntent

sealed class WeatherIntent : MviIntent {
    object Initial : WeatherIntent()
}