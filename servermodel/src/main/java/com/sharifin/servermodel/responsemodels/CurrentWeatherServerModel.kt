package com.sharifin.servermodel.responsemodels

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CurrentWeatherServerModel(
        @Json(name = "coord")
        val coord: CoordServerModel? = null,
        @Json(name = "weather")
        val weather: ArrayList<WeatherServerModel>? = ArrayList(),
        @Json(name = "base")
        val base: String? = "",
        @Json(name = "main")
        val main: MainServerModel? = null,
        @Json(name = "visibility")
        val visibility: Int? = -1,
        @Json(name = "wind")
        val wind: WindServerModel? = null,
        @Json(name = "clouds")
        val clouds: CloudsServerModel? = null,
        @Json(name = "dt")
        val dt: Long?=-1,
        @Json(name = "sys")
        val sys: SysServerModel? = null,
        @Json(name = "id")
        val id: Long? = null,
        @Json(name = "name")
        val name: String? = null,
        @Json(name = "cod")
        val cod: Int? = -1
)

@JsonSerializable
data class SysServerModel(
        @Json(name = "type")
        val type: Int? = -1,
        @Json(name = "id")
        val id: Int? = -1,
        @Json(name = "message")
        val message: Double? = -0.1,
        @Json(name = "country")
        val country: String? = "",
        @Json(name = "sunrise")
        val sunrise: Int? =-1,
        @Json(name = "sunset")
        val sunset: Int?= -1
)

@JsonSerializable
data class CloudsServerModel(
        @Json(name = "all")
        val all: Int? = -1
)

@JsonSerializable
data class WindServerModel(
        @Json(name = "speed")
        val speed: Double?=-0.1,
        @Json(name = "deg")
        val deg: Double?=-0.1
)

@JsonSerializable
data class MainServerModel(
        @Json(name = "temp")
        val temp: Double? = -0.1,
        @Json(name = "pressure")
        val pressure: Double? = -0.1,
        @Json(name = "humidity")
        val humidity: Int? = -1,
        @Json(name = "temp_min")
        val tempMin: Double? = -0.1,
        @Json(name = "temp_max")
        val tempMax: Double? = -0.1
)

@JsonSerializable
data class WeatherServerModel(
        @Json(name = "id")
        val id: Long? = -1,
        @Json(name = "main")
        val main: String?="",
        @Json(name = "description")
        val description: String?="",
        @Json(name = "icon")
        val icon: String?=""
)

@JsonSerializable
data class CoordServerModel(
        @Json(name = "lon")
        val lon: Double? = -0.1,
        @Json(name = "lat")
        val lat: Double? = -0.1
)