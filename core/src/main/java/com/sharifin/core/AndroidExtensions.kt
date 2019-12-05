@file:JvmName("AndroidUtils")
package com.sharifin.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import org.apache.commons.lang.StringEscapeUtils

fun Fragment.hideKeyboard() {
    if (activity != null) {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null)
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}

fun Fragment.showKeyboard() {

    if (activity != null) {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    imm.hideSoftInputFromWindow(view?.windowToken, 0)
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
}

fun Activity.permissionGranted(permission: String): Boolean =
        ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.permissionNotGranted(permission: String): Boolean =
        ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED

fun Activity.requestPermissions(requestCode: Int, permissions: Array<String>) =
        ActivityCompat.requestPermissions(this, permissions, requestCode)

fun Activity.shouldShowPermissionRationale(permission: String) =
        ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

infix fun Intent.startActivity(context: Context) {
    context.startActivity(this)
}

infix fun Intent.startActivity(fragment: Fragment) {
    fragment.startActivity(this)
}

infix fun Context.pxToDp(px: Float) =
        px / (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()

infix fun Context.dpToPx(dp: Float) =
        dp * (resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT).toFloat()

fun Context.getAppVersion(): String =
        try {
            val packageInfo =
                    packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            ""
        }

fun stripHTML(html: String): String =
    StringEscapeUtils.unescapeHtml(html)


fun Intent.canBeLaunched(context : Context) : Boolean {
    val info = resolveActivityInfo(context.packageManager, flags)
    return info != null && info.exported
}