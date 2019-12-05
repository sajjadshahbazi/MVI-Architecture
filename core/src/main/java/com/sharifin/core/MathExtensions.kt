package com.sharifin.core


fun Long.abs() =
        Math.abs(this)

fun Double.abs() =
        Math.abs(this)

fun Float.abs() =
        Math.abs(this)

fun Int.abs() =
        Math.abs(this)

infix fun Int.powerOf(power : Double) =
        Math.pow(this.toDouble(), power)

infix fun Double.powerOf(power : Double) =
        Math.pow(this, power)

infix fun Long.powerOf(power : Double) =
        Math.pow(this.toDouble(), power)

infix fun Float.powerOf(power : Double) =
        Math.pow(this.toDouble(), power)