package com.sharifin.core

import android.text.format.DateFormat
import com.sharifin.calendar.CalendarUtils
import retrofit2.Retrofit
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


inline fun <reified T> Retrofit.create(): T =
    create(T::class.java)

fun Int.priceAnnotator(postFix: String = "ریال", prefix: String = "", postFixDivider: String = " ", prefixDivider: String = ""): String {
    val numberFormat = NumberFormat.getInstance(Locale.ENGLISH)
    return prefix + prefixDivider + numberFormat.format(this) + postFixDivider + postFix
}

fun Long.priceAnnotator(postFix: String = "ریال", prefix: String = "", postFixDivider: String = " ", prefixDivider: String = ""): String {
    val numberFormat = NumberFormat.getInstance(Locale.ENGLISH)
    return prefix + prefixDivider + numberFormat.format(this) + postFixDivider + postFix
}

fun String.isValidLong(): Boolean {
    var res: Boolean = false
    try {
        this.toLong()
        res = true
    } catch (e: Exception) {
        res = false
    } finally {
        return res
    }
}

fun String.priceAnnotator(postFix: String = "ریال", prefix: String = "", postFixDivider: String = " ", prefixDivider: String = ""): String =
    (toLongOrNull() ?: 0L)
        .let {
            NumberFormat.getInstance(Locale.ENGLISH)
                .run {
                    prefix + prefixDivider + format(it) + postFixDivider + postFix
                }
                .toString()
        }

val standardDateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
val standardDateFormatterWithoutTime = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun String.standardDateToEpoch(): Long =
    standardDateFormatter.parse(this).time

fun String.standardDateToEpochWithoutTime(): Long =
    standardDateFormatterWithoutTime.parse(this).time

fun Long.standardDateWithoutTime(): String {
    val calendar = Calendar.getInstance()
    calendar.setTimeInMillis(this)
    return standardDateFormatterWithoutTime.format(calendar.time)
}

fun String.standardDateToPersianDateWithoutTime(useMonthName: Boolean): String {

    return this.standardDateToEpochWithoutTime().toPersianDateOnly(useMonthName)

//    val calendar = Calendar.getInstance()
//    calendar.setTimeInMillis(standardDateFormatterWithoutTime.parse(this).time)
//    return standardDateFormatterWithoutTime.format(calendar.time)
}

fun Long.toPersianDateOnly(useMonthName: Boolean): String =
    if (!useMonthName) {
        toPersianDateOnly()
    } else {
        CalendarUtils.datetimeToPersianString(this)
    }

fun String.standardDateToPersianDateOnly(useMonthName: Boolean = true): String =
    standardDateToEpoch()
        .toPersianDateOnly(useMonthName)

fun String.toPersianDateAndTime(useMonthName: Boolean = true, delimiter: String = " "): String =
    standardDateToEpoch().let {
        it.toPersianTimeOnly()+ delimiter + it.toPersianDateOnly(useMonthName)
    }

fun String.compareToSystemTime(): Compare {
    val systemTime = System.currentTimeMillis()
    return when {
        standardDateToEpoch() < systemTime -> Compare.Lesser
        standardDateToEpoch() == systemTime -> Compare.Equal
        else -> Compare.Greater
    }

}

fun Long.toPersianDate(): String =
    Calendar.getInstance()
        .let {
            it.timeInMillis = this
            DateFormat
                .format("yyyy/MM/dd HH:mm:ss", it)
                .toString()
        }

fun Long.toPersianDateOnly(): String =
    CalendarUtils.datetimeToString(this)

fun Long.toPersianTimeOnly(): String =
    Calendar.getInstance()
        .let {
            it.timeInMillis = this
            DateFormat
                .format("HH:mm", it)
                .toString()
        }

fun String.cardPanFormatter(delimiter: Char = '-'): String {
    val formattedPan = CharArray(19)
    val stringPan = this.toString()
    when {
        stringPan.length in 1..4 -> {
            return stringPan
        }
        stringPan.length in 5..8 -> {
            var count = 0
            val formattedPan = CharArray(stringPan.length + 1)
            for (i in 0 until stringPan.length + 1) {
                if (i % 5 == 4) {
                    formattedPan[i] = (delimiter)
                } else {
                    formattedPan[i] = (stringPan[count])
                    count++
                }
            }
            return String(formattedPan)
        }
        stringPan.length in 9..12 -> {
            var count = 0
            val formattedPan = CharArray(stringPan.length + 2)
            for (i in 0 until stringPan.length + 2) {
                if (i % 5 == 4) {
                    formattedPan[i] = (delimiter)
                } else {
                    formattedPan[i] = (stringPan[count])
                    count++
                }
            }
            return String(formattedPan)
        }
        stringPan.length in 12..16 -> {
            var count = 0
            val formattedPan = CharArray(stringPan.length + 3)
            for (i in 0 until stringPan.length + 3) {
                if (i % 5 == 4) {
                    formattedPan[i] = (delimiter)
                } else {
                    formattedPan[i] = (stringPan[count])
                    count++
                }
            }
            return String(formattedPan)
        }
    }
    return ""
}

fun String.toNumberString(): String {
    var temp2 = ""
    forEachIndexed { index, char ->
        when (char) {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                temp2 += char
            }
        }
    }
    return temp2
}

fun String.toLongNumber(): Long {
    return toNumberString().toLongOrNull() ?: 0
}

fun String.toPlainNumber(): String =
    toNumberString()

fun String.toNumber(): Long {
    return toNumberString().toLongOrNull() ?: 0L
}


fun String.isValidPrice(): Boolean =
    toNumberString()
        .toLongOrNull()
        ?.let {
            it in 9999 until 30_000_000
        }
        ?: false


fun String.isValidCardNumber(): Boolean =
    toNumberString()
        .toLongOrNull()
        ?.let {
            it in 1000000000000001..9999999999999999
        }
        ?: false

fun String.isCardValid(): Boolean {
    var sum = 0
    val cardNumber = this.toNumberString()
    if (cardNumber.length != 16)
        return false

    val chars = cardNumber.toCharArray()

    for (i in 0 until chars.size)
        sum += if (i % 2 == 0) {
            val temp = chars[i].toString().toInt() * 2
            if (temp > 9) {
                (temp - 9)
            } else {
                temp
            }
        } else {
            chars[i].toString().toInt()
        }
    return sum % 10 == 0

}

fun String.isDigit() =
    toLongOrNull() != null


infix fun Double.format(decimal: Int): String {
    return String.format("%.${decimal}f", this)
}

infix fun Float.format(decimal: Int): String {
    return String.format("%.${decimal}f", this)
}


fun String.parseDate() =
    substring(0, 4) +
        "/" +
        substring(4, 6) +
        "/" +
        substring(6, 8)

fun String.parseDateAndTime() =
    parseDate() +
        "-" +
        substring(8, 10) +
        ":" +
        substring(10, 12) +
        ":" +
        substring(12, 14)

fun String.backToNumberDate() =
    replace("/", "")
        .replace(":", "")

fun Long.getTime(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val min = calendar.get(Calendar.MINUTE)
    val hourString = if (hour > 9)
        hour.toString()
    else
        "0$hour"
    val minString = if (min > 9)
        min.toString()
    else
        "0$min"

    return "$hourString:$minString"
}

fun String.phoneFormatter(delimiter: Char = ' '): String {
    val formattedPan = CharArray(13)
    val stringPan = this.toString()
    when {
        stringPan.length in 1..4 -> {
            return stringPan
        }
        stringPan.length in 5..7 -> {
            var count = 0
            val formattedPan = CharArray(stringPan.length + 1)
            for (i in 0 until stringPan.length + 1) {
                if (i == 4) {
                    formattedPan[i] = (delimiter)
                } else {
                    formattedPan[i] = (stringPan[count])
                    count++
                }
            }
            return String(formattedPan)
        }
        stringPan.length in 8..11 -> {
            var count = 0
            val formattedPan = CharArray(stringPan.length + 2)
            for (i in 0 until stringPan.length + 2) {
                if (i == 4 || i == 8) {
                    formattedPan[i] = (delimiter)
                } else {
                    formattedPan[i] = (stringPan[count])
                    count++
                }
            }
            return String(formattedPan)
        }
    }
    return ""
}


enum class Compare {
    Greater,
    Lesser,
    Equal
}
