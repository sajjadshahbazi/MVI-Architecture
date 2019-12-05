@file:JvmName("Base64Extensions")

package com.sharifin.core

import android.util.Base64

fun ByteArray.string(): String = String(this)

fun String.base64Decode(): String = Base64.decode(this, Base64.NO_WRAP).string()

fun ByteArray.base64Decode(): String = Base64.decode(this, Base64.NO_WRAP).string()

fun String.base64Encode(): String = Base64.encode(this.toByteArray(), Base64.NO_WRAP).string()

fun ByteArray.base64Encode(): String = Base64.encode(this, Base64.NO_WRAP).string()

fun String.base64DecodeToByteArray(): ByteArray = Base64.decode(this, Base64.NO_WRAP)

fun ByteArray.base64DecodeToByteArray(): ByteArray = Base64.decode(this, Base64.NO_WRAP)

fun String.base64EncodeToByteArray(): ByteArray = Base64.encode(this.toByteArray(), Base64.NO_WRAP)

fun ByteArray.base64EncodeToByteArray(): ByteArray = Base64.encode(this, Base64.NO_WRAP)

