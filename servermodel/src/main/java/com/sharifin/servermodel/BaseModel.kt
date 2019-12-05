package com.sharifin.servermodel

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

//@JsonClass(generateAdapter = BuildConfig.GENERATE_MOSHI_KOTLIN)
@JsonSerializable
data class BaseModel<T>(
        @Json(name = "data")
        val data: T? = null,

        @Json(name = "detail")
        val detail: String? = null
)
