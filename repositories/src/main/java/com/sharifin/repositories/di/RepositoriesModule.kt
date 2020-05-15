package com.sharifin.repositories.di

import dagger.Module


@Module(includes = [
    AcharehModule::class,
    AndroidRepoModule::class,
    WeatherModule::class
])
abstract class RepositoriesModule {

}