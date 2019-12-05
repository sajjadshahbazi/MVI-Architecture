package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonDefaultValueLong
import se.ansman.kotshi.JsonDefaultValueString
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class UserServerModel(
        @JsonDefaultValueLong(-1)
        @Json(name = "id")
        val id: Long,
        @JsonDefaultValueString("")
        @Json(name = "first_name")
        val firstName: String,
        @JsonDefaultValueString("")
        @Json(name = "last_name")
        val lastName: String,
        @JsonDefaultValueString("")
        @Json(name = "phone")
        val phone: String,
        @JsonDefaultValueString("")
        @Json(name = "avatar")
        val avatar: String,
        @JsonDefaultValueString("")
        @Json(name = "full_name")
        val fullName: String
)