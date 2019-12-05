package com.example.signupuser.app_di

interface Factory<T>{

    fun create() : T
}