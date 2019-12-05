package com.example.signupuser.app_di

import okhttp3.Interceptor

class HeaderInterceptor : Factory<Interceptor>{
    override fun create(): Interceptor  =
        Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Basic MDk4MjIyMjIyMjI6c2FuYTEyMzQ=")
                .build()
            chain.proceed(newRequest)
        }
}