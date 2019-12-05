package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonDefaultValueInt
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class AndroidVersionServerModel(
        @JsonDefaultValueInt(-1)
        @Json(name="last")
        val last : Int,
        @JsonDefaultValueInt(-1)
        @Json(name="min")
        val min : Int
)