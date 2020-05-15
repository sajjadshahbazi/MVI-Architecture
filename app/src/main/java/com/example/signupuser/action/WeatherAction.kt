package com.example.signupuser.action

import com.sharifin.base.BaseAction

sealed class WeatherAction : BaseAction() {
    object Initial : WeatherAction()
    object CurrentWeather : WeatherAction()
}