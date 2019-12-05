package com.sharifin.core

interface BiMapper<T, R> {

    fun mapTo(item: T): R

    fun mapBack(item: R): T
}