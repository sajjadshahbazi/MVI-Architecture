package com.sharifin.servermodel.responsemodels

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class HourlyWeatherServerModel(
        @Json(name = "cod")
        val cod: String,
        @Json(name = "message")
        val message: Double,
        @Json(name = "cnt")
        val cnt: Int,
        @Json(name = "list")
        val list: ArrayList<WeatherHourlyDetailsServerModel> = ArrayList(),
        @Json(name = "city")
        val city: CityServerModel
)

data class CityServerModel(
        @Json(name = "id")
        val id: Long? = -1,
        @Json(name = "name")
        val name: String? = "",
        @Json(name = "coord")
        val coord: CoordServerModel? = null,
        @Json(name = "country")
        val country: String? = ""
)

data class WeatherHourlyDetailsServerModel(
        @Json(name = "dt")
        val dt: Long? = -1,
        @Json(name = "main")
        val main: MainServerModel? = null,
        @Json(name = "weather")
        val weather: ArrayList<WeatherServerModel> = ArrayList(),
        @Json(name = "clouds")
        val clouds: CloudsServerModel? = null,
        @Json(name = "wind")
        val wind: WindServerModel? = null,
        @Json(name = "dt_txt")
        val dt_txt: String? = ""
)

