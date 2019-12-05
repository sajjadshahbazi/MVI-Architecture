@file:JvmName("ObservableUtils")

package com.sharifin.core

import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.SchedulerSupport
import java.util.concurrent.TimeUnit

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T : Any, U : Any> Observable<T>.notOfType(clazz: Class<U>): Observable<T> {
    checkNotNull(clazz) { "clazz is null" }
    return filter { !clazz.isInstance(it) }
}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
inline fun <reified U : Any> Observable<*>.notOfType(): Observable<*> {
    return notOfType(U::class.java)
}

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.COMPUTATION)
fun <T : Any> Observable<T>.defaultThrottleFirst(delay: Long = 300): Observable<T> =
        this.throttleFirst(delay, TimeUnit.MILLISECONDS)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.COMPUTATION)
fun <T : Any> defaultLastStableResultWithStart(toBeReturned: T, immediateReturned: T): Observable<T> =
        Observable.just(toBeReturned)
                .delay(2500, TimeUnit.MILLISECONDS)
                .startWith(immediateReturned)

@CheckReturnValue
@SchedulerSupport(SchedulerSupport.COMPUTATION)
fun <T : Any> defaultClicksLastStableResultWithStart(toBeReturned: T, immediateReturned: T): Observable<T> =
        Observable.just(toBeReturned)
                .delay(300, TimeUnit.MILLISECONDS)
                .startWith(immediateReturned)


