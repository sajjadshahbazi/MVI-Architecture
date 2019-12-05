package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class StateObject(
    @Json(name = "state_id")
    var stateId: Int = 0,
    @Json(name = "state_name")
    val stateName: String = ""
)