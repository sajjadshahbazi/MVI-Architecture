package com.example.signupuser.view.weather.mapper

import com.example.signupuser.view.weather.models.HourlyUiModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.WeatherHourlyDetailsRepoModel
import java.util.*
import javax.inject.Inject

class HourlyWeatherRepoUiMapper @Inject constructor() : Mapper<
        WeatherHourlyDetailsRepoModel,
        HourlyUiModel
        > {
    override fun map(item: WeatherHourlyDetailsRepoModel): HourlyUiModel {

        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = item.dt * 1000L
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        return HourlyUiModel(
                iconUrl = "http://openweathermap.org/img/wn/${item.weather.first().icon}@2x.png",
                temp = "${(item.main.temp - 273.15)}",
                time = hour.toString()
        )
    }

}