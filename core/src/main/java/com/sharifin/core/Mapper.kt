package com.sharifin.core

interface Mapper<T : Any?, R> {

    fun map(item: T): R
}
