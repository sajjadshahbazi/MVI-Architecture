package com.sharifin.repositories.cache

import android.content.Context
import android.preference.PreferenceManager

interface AESSharedPrefExt {

    infix fun Context.getAESKey(phone: String): String =
            PreferenceManager.getDefaultSharedPreferences(this)
                    .getString("AES_$phone", "")!!

    fun Context.putAESKey(phone: String, key: String): Boolean =
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .putString("AES_$phone", key)
                    .commit()

    fun Context.removeAESKey(phone : String) : Boolean =
            PreferenceManager.getDefaultSharedPreferences(this)
                    .edit()
                    .remove("AES_$phone")
                    .commit()
}