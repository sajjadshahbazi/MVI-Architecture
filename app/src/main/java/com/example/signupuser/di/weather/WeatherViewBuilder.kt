package com.example.signupuser.di.weather

import com.example.signupuser.view.weather.WeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherViewBuilder{
    @ContributesAndroidInjector(modules = [
        WeatherActivityAbstractModule::class
    ])
    internal abstract fun provideWeatherActivity(): WeatherActivity
}