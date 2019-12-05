package com.sharifin.repositories.cache

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable

interface Persister<T> {

    fun observe() : Observable<T>

    fun getOnce() : Maybe<T>

    fun persist(items : T) : Completable
}