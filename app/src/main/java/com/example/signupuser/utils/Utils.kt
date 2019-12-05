package com.sharifin.mall.utils


import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import java.util.*


object Utils {

    // some value
    val uniqueDeviceID: String
        get() {
            val m_szDevIDShort =
                "35" + Build.BOARD.length % 10 + Build.BRAND.length % 10 + Build.CPU_ABI.length % 10 + Build.DEVICE.length % 10 + Build.MANUFACTURER.length % 10 + Build.MODEL.length % 10 + Build.PRODUCT.length % 10
            var serial: String? = null
            try {
                serial = android.os.Build::class.java.getField("SERIAL").get(null).toString()
                return UUID(m_szDevIDShort.hashCode().toLong(), serial.hashCode().toLong()).toString()
            } catch (exception: Exception) {
                serial = "serial"
            }

            return UUID(m_szDevIDShort.hashCode().toLong(), serial!!.hashCode().toLong()).toString()
        }

    fun getAppVersionName(context: Context): String {
        var versionName = ""
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            versionName = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            throw RuntimeException("Could not get package name: $e")
        }

        return versionName
    }

    fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
        try {
            packageManager.getPackageInfo(packageName, 0)
            return true
        } catch (e: Exception) {
            return false
        }

    }

    fun getFromMarket(packageName: String, context: Context) {
        try {
            context.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                    "market://details?id=$packageName"
                            )))
        } catch (anfe: android.content.ActivityNotFoundException) {
            context.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                    "https://play.google.com/store/apps/details?id=$packageName"
                            )))
        }

    }
}
