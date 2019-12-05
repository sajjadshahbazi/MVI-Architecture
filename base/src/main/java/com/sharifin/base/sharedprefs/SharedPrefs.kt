package com.sharifin.base.sharedprefs

import io.reactivex.Single

interface SharedPrefs {

    fun setPhone(phone: String)
    fun getPhone(): Single<String>
    fun setTwoStep(isTwoStep: Boolean)
    fun getTwoStep(): Single<Boolean>
    fun setToken(token: String)
    fun getToken(): String
    fun setNonce(nonceDiff: Long)
    fun setServerPublicKey(serverPublicKey: String)
    fun getServerPublicKey(): String
    fun getNonce(): Long
    fun setLockSecurityOption()
    fun getLockSecurityOption(): Boolean


    fun getIVParam(): String
    fun setIVParam(ivParam: String)

    fun dontShowFingerprintAvailable() : Boolean
    fun dontShowFingerprintAvailable(dontShow : Boolean)

    fun setDefaultWallet(phone : String, wallet: String)
    fun getDefaultWallet(phone: String): String
}