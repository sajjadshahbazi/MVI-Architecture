package com.example.signupuser.view.weather.mapper

import com.example.signupuser.view.weather.models.DailyUiModel
import com.sharifin.core.Mapper
import com.sharifin.repositories.weather.repomodel.WeatherDailyDetailsRepoModel
import com.sharifin.servermodel.responsemodels.DailyWeatherServerModel
import com.sharifin.servermodel.responsemodels.WeatherDailyDetailsServerModel
import javax.inject.Inject

class DailyWeatherRepoUiMapper @Inject constructor() : Mapper<
        WeatherDailyDetailsRepoModel,
        DailyUiModel
        > {

    override fun map(item: WeatherDailyDetailsRepoModel): DailyUiModel =
        DailyUiModel(
                iconUrl = "http://openweathermap.org/img/wn/${item.weather.first().icon}@2x.png" ,
                tvDay = "",
                minTemp = " / ${
                (item.temp.min - 273.15)
                }",
                maxTemp ="${
                (item.temp.max - 273.15)
                }"
        )
}