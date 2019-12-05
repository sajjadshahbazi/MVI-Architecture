package com.sharifin.core

interface Factory<in T, out V> {
    fun create(item : T) : V
}