package com.sharifin.base.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Single
import javax.inject.Inject


private const val MOBANK_SHARED_PREF = "mbank"

class SharedPrefsImpl @Inject constructor(
        private val context: Context
) : SharedPrefs {

    override fun setLockSecurityOption() {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean("lock_security_check", true)
                .apply()
    }

    override fun getLockSecurityOption(): Boolean =
            context.getSharedPreferences(
                    MOBANK_SHARED_PREF, Context.MODE_PRIVATE
            ).getBoolean("lock_security_check", false)

    override fun getIVParam(): String =
            context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                    .getString("IVParam", "")
                    ?: ""


    override fun setIVParam(ivParam: String) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putString("IVParam", ivParam)
                .apply()
    }

    override fun setNonce(nonceDiff: Long) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putLong("nonce", nonceDiff)
                .apply()
    }

    override fun setServerPublicKey(serverPublicKey: String) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putString("server_key", serverPublicKey)
                .apply()

    }

    override fun getServerPublicKey(): String {

        return context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .getString("server_key", "") ?: ""
    }

    override fun getNonce(): Long {
        return context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .getLong("nonce", 0)
    }

    override fun getPhone(): Single<String> {
        val phone = context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .getString("phone", "")

        return Single.just(phone)
    }

    override fun getTwoStep(): Single<Boolean> {
        val isTwoStep = context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .getBoolean("is_two_step", false)
        return Single.just(isTwoStep)
    }

    override fun setPhone(phone: String) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putString("phone", phone)
                .apply()
    }

    override fun setTwoStep(isTwoStep: Boolean) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putBoolean("is_two_step", isTwoStep)
                .apply()
    }

    override fun setToken(token: String) {
        context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply()
    }

    override fun getToken(): String {

        return context
                .getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                .getString("token", "") ?: ""
    }

    override fun dontShowFingerprintAvailable(): Boolean =
            context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                    .getBoolean("dont_show_fingerprint_available", false)

    override fun dontShowFingerprintAvailable(dontShow: Boolean) =
            context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean("dont_show_fingerprint_available", dontShow)
                    .apply()

    override fun setDefaultWallet(phone: String, wallet: String) {
        getSharedPrefs()
                .edit()
                .putString("wallet_$phone", wallet)
                .apply()
    }

    override fun getDefaultWallet(phone: String): String =
            getSharedPrefs()
                    .getString("wallet_$phone", "") ?: ""


    private fun getSharedPrefs(): SharedPreferences =
            context.getSharedPreferences(MOBANK_SHARED_PREF, Context.MODE_PRIVATE)

}