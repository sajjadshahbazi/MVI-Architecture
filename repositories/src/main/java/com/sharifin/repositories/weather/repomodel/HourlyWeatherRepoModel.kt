package com.sharifin.repositories.weather.repomodel

data class HourlyWeatherRepoModel(
        val cod: String,
        val message: Double,
        val cnt: Int,
        val list: ArrayList<WeatherHourlyDetailsRepoModel>,
        val city: CityRepoModel
)

data class WeatherHourlyDetailsRepoModel(
        val dt: Long,
        val main: MainRepoModel,
        val weather: ArrayList<WeatherRepoModel>,
        val dtTxt: String
)