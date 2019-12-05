package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonDefaultValueString
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class PaginateModel<T>(
        @JsonDefaultValueString("")
        @Json(name = "next")
        val next : String,
        @Json(name = "results")
        val result: T,
        @Json(name = "count")
        val count: Int)