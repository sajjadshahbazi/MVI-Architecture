package com.sharifin.repositories.weather.repomodel

import com.squareup.moshi.Json

data class CurrentWeatherRepoModel(
        val weather: ArrayList<WeatherRepoModel>,
        val base: String,
        val main: MainRepoModel,
        val visibility: Int,
        val dt: Long,
        val id: Long,
        val name: String,
        val cod: Int
)


data class MainRepoModel(
        @Json(name = "temp")
        val temp: Double,
        @Json(name = "pressure")
        val pressure: Double,
        @Json(name = "humidity")
        val humidity: Int,
        @Json(name = "temp_min")
        val tempMin: Double,
        @Json(name = "temp_max")
        val tempMax: Double
)

data class WeatherRepoModel(
        @Json(name = "id")
        val id: Long,
        @Json(name = "main")
        val main: String,
        @Json(name = "description")
        val description: String,
        @Json(name = "icon")
        val icon: String
)
