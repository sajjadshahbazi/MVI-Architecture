package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CityObject(
    @Json(name = "city_id")
    var cityId: Int = 0,
    @Json(name = "city_name")
    val cityName: String = ""
)