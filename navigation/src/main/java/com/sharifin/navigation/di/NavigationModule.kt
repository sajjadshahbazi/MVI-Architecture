package com.sharifin.navigation.di

import com.sharifin.navigation.Navigation
import com.sharifin.navigation.NavigationImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class NavigationModule {

    @Binds
    @Singleton
    abstract fun bindNavigation(
        navigation: NavigationImpl
    ): Navigation
}