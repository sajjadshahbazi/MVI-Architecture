package com.sharifin.retrofit.retrofitutils

import io.reactivex.functions.BiPredicate
import java.net.ConnectException
import java.net.ProtocolException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class RetrofitRetryHandler(private val int: Int) : BiPredicate<Int, Throwable> {

    override fun test(integer: Int, throwable: Throwable): Boolean =
            integer < int && (throwable is SocketTimeoutException ||
                throwable is TimeoutException ||
                throwable is ConnectException ||
                throwable is UnknownHostException ||
                throwable is ProtocolException)

    companion object {
        @JvmStatic
        fun createDefault() = RetrofitRetryHandler(3)
    }
}
