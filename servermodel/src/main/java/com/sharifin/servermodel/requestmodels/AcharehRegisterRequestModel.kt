package com.sharifin.servermodel.requestmodels

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AcharehRegisterRequestModel(
    @Json(name = "region")
    var region: Int = 1,
    @Json(name = "address")
    val address: String = "",
    @Json(name = "lat")
    val lat: Double = 0.0,
    @Json(name = "lng")
    val lng: Double = 0.0,
    @Json(name = "coordinate_mobile")
    val coordinateMobile: String = "",
    @Json(name = "coordinate_phone_number")
    val coordinatePhoneNumber: String = "",
    @Json(name = "first_name")
    val firstName: String = "",
    @Json(name = "last_name")
    val lastName: String = "",
    @Json(name = "gender")
    val gender: String = ""
)