package com.example.signupuser.app_di

import com.example.signupuser.di.ListViewBuilder
import com.example.signupuser.di.MapViewBuilder
import com.example.signupuser.di.ProfileViewBuilder
import com.example.signupuser.di.weather.WeatherViewBuilder
import dagger.Module

@Module(includes = [
    ProfileViewBuilder::class,
    MapViewBuilder::class,
    ListViewBuilder::class,
    WeatherViewBuilder::class
])
abstract class BuildersModule