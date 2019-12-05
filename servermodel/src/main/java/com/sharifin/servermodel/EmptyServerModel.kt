package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonDefaultValueInt
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class EmptyServerModel(
        @JsonDefaultValueInt(0)
        @Json(name="dummy")
        val dummy : Int
)