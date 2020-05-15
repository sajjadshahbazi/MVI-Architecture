package com.example.signupuser.state

import com.example.signupuser.view.weather.models.CurrentWeatherUiModel
import com.example.signupuser.view.weather.models.DailyUiModel
import com.example.signupuser.view.weather.models.HourlyUiModel
import com.sharifin.base.BaseState
import com.sharifin.base.mvibase.MviViewState

data class WeatherState(
        override val base: BaseState,
        val currentUIWeather : CurrentWeatherUiModel?,
        val hourlyUiModel : List<HourlyUiModel>,
        val dailyUiModel : List<DailyUiModel>,
        val city : String

) : MviViewState {

    companion object {
        @JvmStatic
        fun idle() =
                WeatherState(
                        BaseState.stable(),
                        currentUIWeather = null,
                        hourlyUiModel = emptyList(),
                        dailyUiModel = emptyList(),
                        city = ""
                )
    }
}