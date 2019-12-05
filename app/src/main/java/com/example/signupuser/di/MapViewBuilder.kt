package com.example.signupuser.di

import com.example.signupuser.view.MapActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MapViewBuilder{
    @ContributesAndroidInjector(modules = [
        MapActivityAbstarctModule::class
    ])
    internal abstract fun provideMapActivity(): MapActivity
}