package com.sharifin.repositories.di

import com.sharifin.repositories.android.AndroidRepo
import com.sharifin.repositories.android.AndroidRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AndroidRepoModule {

    @Binds
    abstract fun bindAndroidRepo(repo: AndroidRepoImpl):
            AndroidRepo
}