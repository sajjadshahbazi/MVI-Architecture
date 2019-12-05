package com.sharifin.repositories.cache

interface AppCacheCryptor {

    fun encrypt(phone: String, data: String, tries : Int = 0): String

    fun decrypt(phone: String, encrypted: String, tries : Int = 0): String

}