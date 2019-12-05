package com.sharifin.retrofit

import com.sharifin.retrofit.retrofitutils.RetrofitRetryHandler
import com.sharifin.retrofit.retrofitutils.createErrorResponse
import io.reactivex.Single
import retrofit2.Response
import java.io.EOFException

fun <T> Single<Response<T>>.defaultRetrofitRetry(): Single<Response<T>> =
    retry(RetrofitRetryHandler.createDefault())
            .onErrorReturn {
                if (it is EOFException) {
                    Response.success(null)
                } else {
                    createErrorResponse(it)
                }
            }

val Response<*>.isNotSuccessful: Boolean
    get() {
        return !isSuccessful
    }

val Response<*>.isBodyEmpty: Boolean
    get() {
        return body() == null
    }