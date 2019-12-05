@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package com.sharifin.repositories.cache

import com.sharifin.base.sharedprefs.SharedPrefs
import com.squareup.moshi.Moshi
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables.combineLatest
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import javax.inject.Inject

inline class Phone(val phone: String)
inline class Data(val data: String)

abstract class BasePersister<T> : Persister<T> {

    @Inject
    lateinit var prefs: SharedPrefs
    @Inject
    lateinit var cryptor: AppCacheCryptor
    @Inject
    lateinit var moshi: Moshi

    override fun observe(): Observable<T> =
            prefs.getPhone()
                    .subscribeOn(Schedulers.computation())
                    .toObservable()
                    .publish { publish ->
                        //second parameter is phone and first is the data received from db
                        combineLatest(
                                publish.switchMap { observeChanges(it) }.map { Data(it) },
                                publish.map { Phone(it) })
                    }
                    .map {
                        cryptor.decrypt(it.second.phone, it.first.data)
                    }
                    .filter { it.isNotBlank() }
                    .map {
                        val type = getActualType()
                        moshi.adapter<T>(type).fromJson(it)!!
                    }
                    .doOnError {
                        Timber.d(it, "observe failed in persister")
                    }


    override fun getOnce(): Maybe<T> =
            prefs.getPhone()
                    .subscribeOn(Schedulers.computation())
                    .map {
                        Phone(it)
                    }
                    .zipWith(prefs.getPhone().flatMap { observeChanges(it).firstOrError() }.map { Data(it) })
                    .map {
                        cryptor.decrypt(it.first.phone, it.second.data)
                    }
                    .filter { it.isNotBlank() }
                    .map {
                        val type = getActualType()
                        moshi.adapter<T>(type).fromJson(it)!!
                    }
                    .doOnError {
                        Timber.d(it, "getOnce failed in persister")
                    }


    override fun persist(items: T): Completable =
            prefs.getPhone()
                    .subscribeOn(Schedulers.computation())
                    .map { phone ->
                        val type = getActualType()

                        val json = moshi.adapter<T>(type).toJson(items)

                        val encrypted = cryptor.encrypt(phone, json)

                        Phone(phone) to Data(encrypted)
                    }
                    .filter { it.second.data.isNotBlank() }
                    .flatMapCompletable {
                        saveData(it.first.phone, it.second.data)
                    }


    protected abstract fun saveData(phone: String, data: String): Completable

    protected abstract fun observeChanges(phone: String): Observable<String>

    private fun <T> Persister<T>.getActualType(): Type =
            (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}