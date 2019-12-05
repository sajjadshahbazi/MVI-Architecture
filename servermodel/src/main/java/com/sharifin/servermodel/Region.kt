package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Region(
    @Json(name = "id")
    var id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "city_object")
    var cityObject: CityObject?,
    @Json(name = "state_object")
    var stateObject: StateObject?
)