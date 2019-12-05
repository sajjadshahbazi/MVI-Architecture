package com.sharifin.repositories.di

import dagger.Module


@Module(includes = [
    AcharehModule::class,
    AndroidRepoModule::class
])
abstract class RepositoriesModule {

}