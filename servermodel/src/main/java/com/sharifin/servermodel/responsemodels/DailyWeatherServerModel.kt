package com.sharifin.servermodel.responsemodels

import com.squareup.moshi.Json

data class DailyWeatherServerModel(
        @Json(name = "cod")
        val cod: String?="",
        @Json(name = "message")
        val message: Int?=-1,
        @Json(name = "city")
        val city: CityServerModel?=null,
        @Json(name = "cnt")
        val cnt: Int?=-1,
        @Json(name = "list")
        val list: List<WeatherDailyDetailsServerModel> = emptyList()
)

data class WeatherDailyDetailsServerModel(
        @Json(name = "dt")
        val dt: Long?=-1,
        @Json(name = "temp")
        val temp: TempServerModel?=null,
        @Json(name = "pressure")
        val pressure: Double?=-0.1,
        @Json(name = "humidity")
        val humidity: Int?=-1,
        @Json(name = "weather")
        val weather: ArrayList<WeatherServerModel> = ArrayList(),
        @Json(name = "speed")
        val speed: Double?=-0.1,
        @Json(name = "deg")
        val deg: Int?=-1,
        @Json(name = "clouds")
        val clouds: Int?=-1,
        @Json(name = "snow")
        val snow: Double?=-0.1
)

data class TempServerModel(
        @Json(name = "day")
        val day: Double?=-0.1,
        @Json(name = "min")
        val min: Double?=-0.1,
        @Json(name = "max")
        val max: Double?=-0.1,
        @Json(name = "night")
        val night: Double?=-0.1,
        @Json(name = "eve")
        val eve: Double?=-0.1,
        @Json(name = "morn")
        val morn: Double?=-0.1
)
