package com.sharifin.core

import android.util.Base64
import javax.inject.Inject


class Base64ToolsImpl @Inject constructor() : Base64Tools {

    override fun stringDecodeToByteArray(str: String): ByteArray = Base64.decode(str, Base64.NO_WRAP)

    override fun byteArrayDecodeToByteArray(byte: ByteArray): ByteArray = Base64.decode(byte, Base64.NO_WRAP)

    override fun stringEncodeToByteArray(str: String): ByteArray = Base64.encode(str.toByteArray(), Base64.NO_WRAP)

    override fun byteArrayEncodeToByteArray(byte: ByteArray): ByteArray = Base64.encode(byte, Base64.NO_WRAP)

    override fun byteArrayEncode(byte: ByteArray): String = Base64.encode(byte, Base64.NO_WRAP).string()

    override fun stringEncode(string: String): String = Base64.encode(string.toByteArray(), Base64.NO_WRAP).string()

    override fun byteArrayDecode(byte: ByteArray): String = Base64.decode(byte, Base64.NO_WRAP).string()

    override fun stringDecode(byte: ByteArray): String = Base64.decode(byte, Base64.NO_WRAP).string()

    override fun decode(string : String): String = string.base64Decode()

}