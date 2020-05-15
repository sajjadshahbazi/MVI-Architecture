package com.sharifin.repositories.weather.repomodel

data class DailyWeatherRepoModel(
        val cod: String? = "",
        val message: Int? = -1,
        val city: CityRepoModel? = null,
        val cnt: Int? = -1,
        val list: ArrayList<WeatherDailyDetailsRepoModel> = ArrayList()
)

data class WeatherDailyDetailsRepoModel(
        val dt: Long,
        val temp: TempRepoModel,
        val pressure: Double? = -0.1,
        val humidity: Int,
        val weather: ArrayList<WeatherRepoModel>,
        val speed: Double,
        val deg: Int,
        val clouds: Int,
        val snow: Double
)

data class TempRepoModel(
        val day: Double,
        val min: Double,
        val max: Double,
        val night: Double,
        val eve: Double,
        val morn: Double
)

data class CityRepoModel(
        val id: Long,
        val name: String,
        val country: String
)