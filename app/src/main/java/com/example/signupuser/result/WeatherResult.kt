package com.example.signupuser.result

import com.example.signupuser.view.weather.models.CurrentWeatherUiModel
import com.example.signupuser.view.weather.models.DailyUiModel
import com.example.signupuser.view.weather.models.HourlyUiModel
import com.sharifin.base.BaseResult
import com.sharifin.retrofit.ErrorHolder

sealed class WeatherResult : BaseResult() {
    object LastStable : WeatherResult()

    data class Error(
            val err: ErrorHolder
    ) : WeatherResult()
    object Loading : WeatherResult()

    data class CurrentWeather(
            val currentUiModel : CurrentWeatherUiModel
    ) : WeatherResult()

    data class HourlyWeather(
            val hourlyUiModel : List<HourlyUiModel>
    ): WeatherResult()

    data class DailyWeather(
            val dailyUiModel : List<DailyUiModel>,
            val city : String
    ): WeatherResult()
}