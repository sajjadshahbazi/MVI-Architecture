package com.sharifin.core



interface Base64Tools{
    fun decode(string : String) : String
    fun stringDecode(byte : ByteArray) : String
    fun byteArrayDecode(byte : ByteArray) : String
    fun stringEncode(byte : String) : String
    fun byteArrayEncode(byte : ByteArray) : String
    fun stringDecodeToByteArray(str : String): ByteArray
    fun byteArrayDecodeToByteArray(byte : ByteArray): ByteArray
    fun stringEncodeToByteArray(str : String): ByteArray
    fun byteArrayEncodeToByteArray(byte : ByteArray): ByteArray
}