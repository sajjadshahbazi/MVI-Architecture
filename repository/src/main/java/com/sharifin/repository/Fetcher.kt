package com.sharifin.repository

import io.reactivex.Single

interface Fetcher<T, R> {

    fun fetch(key : T) : Single<R>
}